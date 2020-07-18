package com.chenxt.bootdemo.service.cache;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenxt.bootdemo.base.api.ICachedPrefix;
import com.chenxt.bootdemo.base.api.RedisApi;
import com.chenxt.bootdemo.entity.Blacklist;
import com.chenxt.bootdemo.entity.Follow;
import com.chenxt.bootdemo.mapper.BlacklistMapper;
import com.chenxt.bootdemo.mapper.FollowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 关注redis service
 *
 * @author chenxt
 * @date 2020/07/18
 */
@Slf4j
@Service
public class FollowCacheService implements ICachedPrefix {
    @Resource
    private FollowMapper followMapper;
    @Resource
    private BlacklistMapper blacklistMapper;

    /**
     * 获取关注用户id列表timeline
     *
     * @param fromUserId 粉丝用户id
     * @param timeline   最后游标
     * @param count      请求数据量
     * @return 关注用户id列表
     */
    public List<Long> getFollowUserIdList(long fromUserId, long timeline, long count) {
        String redisKey = String.format(bootdemo_follow_follow, fromUserId);
        return commonSupplier(redisKey,
                getEmptyFollow(),
                () -> followMapper.selectList(new QueryWrapper<Follow>().lambda().eq(Follow::getFromUserId, fromUserId)),
                (redisConnection, followList) -> {
                    for (Follow follow : followList) {
                        redisConnection.zAdd(redisKey.getBytes(), follow.getUpdatedAt().atZone(ZoneId.systemDefault()).toEpochSecond(), String.valueOf(follow.getToUserId()).getBytes());
                    }
                },
                () -> getIdListByTimeLine(redisKey, timeline, count));
    }

    /**
     * 获取粉丝用户id列表timeline
     *
     * @param toUserId 关注用户id
     * @param timeline 最后游标
     * @param count    请求数据量
     * @return 粉丝id列表
     */
    public List<Long> getFansIdList(long toUserId, long timeline, long count) {
        String redisKey = String.format(bootdemo_follow_fans, toUserId);
        return commonSupplier(redisKey,
                getEmptyFollow(),
                () -> followMapper.selectList(new QueryWrapper<Follow>().lambda().eq(Follow::getToUserId, toUserId)),
                (redisConnection, followList) -> {
                    for (Follow follow : followList) {
                        redisConnection.zAdd(redisKey.getBytes(), follow.getUpdatedAt().atZone(ZoneId.systemDefault()).toEpochSecond(), String.valueOf(follow.getFromUserId()).getBytes());
                    }
                },
                () -> getIdListByTimeLine(redisKey, timeline, count));
    }

    /**
     * 获取所有关注用户id集合
     *
     * @param fromUserId 粉丝用户id
     * @return 关注用户id集合
     */
    public Set<Long> getAllFollowUserIdSet(Long fromUserId) {
        String redisKey = String.format(bootdemo_follow_follow, fromUserId);
        return commonSupplier(redisKey,
                getEmptyFollow(),
                () -> followMapper.selectList(new QueryWrapper<Follow>().lambda().eq(Follow::getFromUserId, fromUserId)),
                (redisConnection, followList) -> {
                    for (Follow follow : followList) {
                        redisConnection.zAdd(redisKey.getBytes(), follow.getUpdatedAt().atZone(ZoneId.systemDefault()).toEpochSecond(), String.valueOf(follow.getToUserId()).getBytes());
                    }
                },
                () -> RedisApi.zrevrange(redisKey, 0, -1).stream().map(id -> Long.parseLong(id.toString())).collect(Collectors.toSet()));
    }

    /**
     * 获取所有粉丝用户id集合
     *
     * @param toUserId 关注用户id
     * @return 粉丝id集合
     */
    public Set<Long> getAllFansIdSet(Long toUserId) {
        String redisKey = String.format(bootdemo_follow_fans, toUserId);
        return commonSupplier(redisKey,
                getEmptyFollow(),
                () -> followMapper.selectList(new QueryWrapper<Follow>().lambda().eq(Follow::getToUserId, toUserId)),
                (redisConnection, followList) -> {
                    for (Follow follow : followList) {
                        redisConnection.zAdd(redisKey.getBytes(), follow.getUpdatedAt().atZone(ZoneId.systemDefault()).toEpochSecond(), String.valueOf(follow.getFromUserId()).getBytes());
                    }
                },
                () -> RedisApi.zrevrange(redisKey, 0, -1).stream().map(id -> Long.parseLong(id.toString())).collect(Collectors.toSet()));
    }

    /**
     * 获取所有拉黑用户id集合
     *
     * @param fromUserId 用户id
     * @return 拉黑用户id集合
     */
    public Set<Long> getAllBlacklistIdSet(Long fromUserId) {
        String redisKey = String.format(bootdemo_follow_blacklist, fromUserId);
        return commonSupplier(redisKey,
                getEmptyBlacklist(),
                () -> blacklistMapper.selectList(new QueryWrapper<Blacklist>().lambda().eq(Blacklist::getFromUserId, fromUserId)),
                (redisConnection, blacklistList) -> {
                    for (Blacklist blacklist : blacklistList) {
                        redisConnection.zAdd(redisKey.getBytes(), blacklist.getUpdatedAt().atZone(ZoneId.systemDefault()).toEpochSecond(), String.valueOf(blacklist.getToUserId()).getBytes());
                    }
                },
                () -> RedisApi.zrevrange(redisKey, 0, -1).stream().map(id -> Long.parseLong(id.toString())).collect(Collectors.toSet()));
    }

    /**
     * 获取所有黑子用户id集合
     *
     * @param toUserId 用户id
     * @return 黑子用户id集合
     */
    public Set<Long> getAllHaterIdSet(Long toUserId) {
        String redisKey = String.format(bootdemo_follow_blacklist, toUserId);
        return commonSupplier(redisKey,
                getEmptyBlacklist(),
                () -> blacklistMapper.selectList(new QueryWrapper<Blacklist>().lambda().eq(Blacklist::getToUserId, toUserId)),
                (redisConnection, blacklistList) -> {
                    for (Blacklist blacklist : blacklistList) {
                        redisConnection.zAdd(redisKey.getBytes(), blacklist.getUpdatedAt().atZone(ZoneId.systemDefault()).toEpochSecond(), String.valueOf(blacklist.getFromUserId()).getBytes());
                    }
                },
                () -> RedisApi.zrevrange(redisKey, 0, -1).stream().map(id -> Long.parseLong(id.toString())).collect(Collectors.toSet()));
    }

    private <T, R> R commonSupplier(String redisKey,
                                    T throughObject,
                                    Supplier<List<T>> queryDbListSupplier,
                                    BiConsumer<RedisConnection, List<T>> handleConsumer,
                                    Supplier<R> queryRedisSupplier) {
        if (isNotExistInCache(redisKey)) {
            List<T> dataList = queryDbListSupplier.get();
            if (dataList.isEmpty()) {
                // 添加防穿透数据
                dataList.add(throughObject);
            }
            pipeline(redisConnection -> handleConsumer.accept(redisConnection, dataList));
            // 防穿透数据设置30s过期时间
            RedisApi.expire(redisKey, 30, TimeUnit.SECONDS);
        }

        return queryRedisSupplier.get();
    }

    private <T> void commonConsumer(String redisKey,
                                    Supplier<List<T>> queryDbListSupplier,
                                    BiConsumer<RedisConnection, List<T>> handleConsumer,
                                    Consumer<Void> redisConsumer) {
        if (isNotExistInCache(redisKey)) {
            List<T> dataList = queryDbListSupplier.get();
            if (CollectionUtils.isEmpty(dataList)) {
                // 调用consumer的都是新增操作，所以不需要防穿透数据
                return;
            }
            pipeline(redisConnection -> handleConsumer.accept(redisConnection, dataList));
        } else {
            // 删除防穿透数据
            if (RedisApi.zrem(redisKey, 0L) > 0) {
                // 解除过期时间
                RedisApi.persist(redisKey);
            }
        }

        redisConsumer.accept(null);
    }

    private List<Long> getIdListByTimeLine(String redisKey, long timeline, long count) {
        // 判断是否是防穿透数据
        if (RedisApi.zSetSize(redisKey) <= 1) {
            // 如果只有一个, 可能是防穿透数据
            Object obj = RedisApi.zSetGetFirst(redisKey);
            if (obj == null || "0".equals(obj.toString())) {
                // 若是防穿透数据，返回空列表
                return Collections.emptyList();
            }
        }
        // 避免score存在0的情况
        timeline = timeline == 0 ? -1 : timeline;
        Long rank = RedisApi.zrevrank(redisKey, timeline);
        long start = rank == null ? 0 : ++rank;
        long end = start + count - 1;
        return RedisApi.zrevrange(redisKey, start, end).stream().map(obj -> Long.parseLong(obj.toString())).collect(Collectors.toList());
    }

    private Blacklist getEmptyBlacklist() {
        Blacklist blacklist = new Blacklist();
        blacklist.setFromUserId(0L);
        blacklist.setToUserId(0L);
        blacklist.setUpdatedAt(LocalDateTime.now());
        return blacklist;
    }

    private Follow getEmptyFollow() {
        Follow follow = new Follow();
        follow.setUpdatedAt(LocalDateTime.now());
        follow.setFromUserId(0L);
        follow.setToUserId(0L);
        return follow;
    }

    private boolean isNotExistInCache(String redisKey) {
        return !RedisApi.exists(redisKey);
    }

    private void pipeline(Consumer<RedisConnection> consumer) {
        RedisApi.execute(redisTemplate -> redisTemplate.executePipelined(
                (RedisCallback<Object>) redisConnection -> {
                    redisConnection.openPipeline();
                    consumer.accept(redisConnection);
                    redisConnection.closePipeline();
                    return null;
                }));
    }
}

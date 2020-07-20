package com.chenxt.bootdemo.base.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * redis工具类
 *
 * @author chenxt
 * @date 2019/07/15
 */
@Component
@Slf4j
public class RedisApi {
    private static RedisTemplate<String, Object> redisTemplate;

    @Resource
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        RedisApi.redisTemplate = redisTemplate;
    }

    /**- - - - - - - - - - - - - - - - - - - - -  Key - - - - - - - - - - - - - - - - - - - -*/

    /**
     * 删除对应的value
     *
     * @param key 键
     */
    public static void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key 键
     * @return 是否存在
     */
    public static boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 查找key
     *
     * @param pattern 正则表达式
     * @return key set
     */
    public static Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 设置过期时间
     *
     * @param key     键
     * @param timeout 过期时长
     * @param unit    时间单位
     */
    public static void expire(String key, long timeout, TimeUnit unit) {
        redisTemplate.expire(key, timeout, unit);
    }

    /**- - - - - - - - - - - - - - - - - - - - -  String - - - - - - - - - - - - - - - - - - - -*/

    /**
     * 写入缓存
     *
     * @param key   键
     * @param value 值
     */
    public static void set(final String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 写入缓存并设置时效时间
     *
     * @param key   键
     * @param value 值
     */
    public static void set(final String key, Object value, Long expireTime, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, expireTime, timeUnit);
    }

    /**
     * 读取缓存
     *
     * @param key 键
     * @return 值
     */
    public static Object get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 自增
     *
     * @param key 键
     * @return 自增后的值
     */
    public static Long incr(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    /**- - - - - - - - - - - - - - - - - - - - -  Hash - - - - - - - - - - - - - - - - - - - -*/

    /**
     * 写入缓存hash
     *
     * @param key     键
     * @param beanMap 全部值
     */
    public static void hSetAll(final String key, BeanMap beanMap) {
        redisTemplate.opsForHash().putAll(key, beanMap);
    }

    /**
     * 读取缓存hash
     *
     * @param key 键
     * @return 全部值
     */
    public static Map<Object, Object> hGetAll(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 哈希 添加
     *
     * @param key     键
     * @param hashKey hash键
     * @param value   值
     */
    public static void hmSet(String key, Object hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 哈希 添加 设置有效时间
     *
     * @param key     键
     * @param hashKey hash键
     * @param value   值
     */
    public static void hmSetWithExpire(String key, Object hashKey, Object value, Long expireTime, TimeUnit timeUnit) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        redisTemplate.expire(key, expireTime, timeUnit);
    }

    /**
     * 哈希获取数据
     *
     * @param key     键
     * @param hashKey hash键
     * @return 值
     */
    public static Object hmGet(String key, Object hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 哈希是否存在key
     *
     * @param key     键
     * @param hashKey hash键
     * @return 是否存在
     */
    public static Boolean hmHasKey(String key, Object hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * 哈希自增
     *
     * @param key     键
     * @param hashKey hash键
     * @param value   值
     * @return 自增后的值
     */
    public static long hIncr(String key, String hashKey, long value) {
        return redisTemplate.opsForHash().increment(key, hashKey, value);
    }

    /**- - - - - - - - - - - - - - - - - - - - -  Hash - - - - - - - - - - - - - - - - - - - -*/

    /**
     * 添加到队首
     *
     * @param key   键
     * @param value 值
     */
    public static void lLeftPush(String key, Object value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 获取列表队首
     *
     * @param key 键
     * @return 值
     */
    public static Object lLeftPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 写入列表队尾
     *
     * @param key    键
     * @param values 值列表
     */
    public static void setList(final String key, List values) {
        redisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * 获取列表全部
     *
     * @param key 键
     * @param <T> 返回列表类型
     * @return 列表全部
     */
    public static <T> List<T> getList(final String key) {
        List<Object> objs = redisTemplate.opsForList().range(key, 0, -1);
        return objs != null ? objs.stream().map(o -> (T) o).collect(Collectors.toList()) : new ArrayList<>();
    }

    /**- - - - - - - - - - - - - - - - - - - - -  Set - - - - - - - - - - - - - - - - - - - -*/

    /**
     * 集合添加
     *
     * @param key   键
     * @param value 值
     */
    public static void sAdd(String key, Object... value) {
        redisTemplate.opsForSet().add(key, value);
    }

    /**
     * 获取集合成员数
     *
     * @param key 键
     * @return 成员数
     */
    public static Long sSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**- - - - - - - - - - - - - - - - - - -  Sorted Set - - - - - - - - - - - - - - - - - - - -*/

    /**
     * 有序集合添加
     *
     * @param key
     * @param value
     * @param score
     */
    public static void zAdd(String key, Object value, double score) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add(key, value, score);
    }

    /**
     * 获取倒序集合
     *
     * @param key
     * @param start
     * @param end   若为-1则是查全部
     * @return
     */
    public static Set<Object> zrevrange(String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    /**
     * 获取元素在有序集合的索引位置
     *
     * @param key
     * @param valueOf
     * @return
     */
    public static Long zrevrank(String key, Object valueOf) {
        return redisTemplate.opsForZSet().reverseRank(key, valueOf);
    }

    /**
     * 有序集合删除
     *
     * @param key
     * @param valueOf
     */
    public static Long zrem(String key, Object valueOf) {
        return redisTemplate.opsForZSet().remove(key, valueOf);
    }

    /**
     * 有序集合计数
     *
     * @param key
     * @return
     */
    public static Long zSetSize(String key) {
        ZSetOperations<String, Object> set = redisTemplate.opsForZSet();
        return set.size(key);
    }

    /**
     * 获取有序集合第一个元素
     *
     * @param key
     * @return
     */
    public static Object zSetGetFirst(String key) {
        ZSetOperations<String, Object> operations = redisTemplate.opsForZSet();
        Set set = operations.range(key, 0, 0);
        if (CollectionUtils.isEmpty(set)) {
            return null;
        }
        return set.iterator().next();
    }

    /**- - - - - - - - - - - - - - - - - - -  事务 - - - - - - - - - - - - - - - - - - - -*/

    public static void execute(Consumer<RedisTemplate<String, Object>> redisTemplateConsumer) {
        redisTemplateConsumer.accept(redisTemplate);
    }

    /**
     * 解除过期时间
     *
     * @param redisKey
     */
    public static void persist(String redisKey) {
        redisTemplate.persist(redisKey);
    }
}

package com.chenxt.bootdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenxt.bootdemo.base.enumeration.FollowRelationEnum;
import com.chenxt.bootdemo.base.expection.BusinessException;
import com.chenxt.bootdemo.base.expection.enumeration.BusinessExceptionCodeEnum;
import com.chenxt.bootdemo.dto.BlacklistDTO;
import com.chenxt.bootdemo.dto.FollowDTO;
import com.chenxt.bootdemo.entity.Follow;
import com.chenxt.bootdemo.mapper.BlacklistMapper;
import com.chenxt.bootdemo.mapper.FollowMapper;
import com.chenxt.bootdemo.service.IFollowService;
import com.chenxt.bootdemo.service.cache.FollowCacheService;
import com.chenxt.bootdemo.vo.BlacklistResultVO;
import com.chenxt.bootdemo.vo.FollowResultVO;
import com.chenxt.bootdemo.vo.FollowStatusVO;
import com.chenxt.bootdemo.vo.FollowVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 关注 服务实现类
 *
 * @author chenxt
 * @date 2020-07-12
 */
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements IFollowService {
    @Resource
    private BlacklistMapper blacklistMapper;
    @Resource
    private FollowCacheService followCacheService;

    @Override
    public List<FollowVO> getFollowListByTimeline(Long userId, long timeline, long count, Long currentUserId) {
        List<Long> followUserIdList = followCacheService.getFollowUserIdList(userId, timeline, count);
        return getFollowVOList(followUserIdList, userId, true, currentUserId);
    }

    @Override
    public List<FollowVO> getFansListByTimeline(Long userId, long timeline, long count, Long currentUserId) {
        List<Long> fansUserIdList = followCacheService.getFansIdList(userId, timeline, count);
        return getFollowVOList(fansUserIdList, userId, false, currentUserId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public FollowResultVO follow(FollowDTO followDTO, Long fromUserId) {
        Long toUserId = followDTO.getToUserId();
        FollowResultVO followResultVO = new FollowResultVO();
        // 初始关系
        FollowRelationEnum relation = getFollowRelationEnum(fromUserId, toUserId);
        switch (relation) {
            case SELF:
                //不允许自己关注自己
                throw new BusinessException(BusinessExceptionCodeEnum.FOLLOW_SELF_NOT_ALLOW);
            case BLACKLISTED:
                // 拉黑对方
                throw new BusinessException(BusinessExceptionCodeEnum.BLACKLISTED_FOLLOW);
            case IS_BLACKLISTED:
            case MUTUAL_BLACKLIST:
                // 被对方拉黑
                // 互相拉黑,被对方拉黑
                throw new BusinessException(BusinessExceptionCodeEnum.IS_BLACKLISTED_FOLLOW);
            case MUTUAL_FOLLOW:
                // 已互相关注
                followResultVO.setRelation(FollowRelationEnum.MUTUAL_FOLLOW.getCode());
                return followResultVO;
            case FOLLOWED:
                // 已关注
                followResultVO.setRelation(FollowRelationEnum.FOLLOWED.getCode());
                return followResultVO;
            default:
        }
        // 走到这里都是未关注, 或被对方关注.

        //关注用户
        Follow follow = new Follow();
        follow.setFromUserId(fromUserId);
        follow.setUpdatedAt(LocalDateTime.now());
        follow.setToUserId(toUserId);
        baseMapper.insertOnDuplicateUpdate(follow);

        // 如果是被关注,   关注后就是互相关注
        if (FollowRelationEnum.IS_FOLLOWED.getCode().intValue() == relation.getCode().intValue()) {
            relation = FollowRelationEnum.MUTUAL_FOLLOW;
        } else {
            // 否则就是已关注
            relation = FollowRelationEnum.FOLLOWED;
        }

        //设置新的关系
        followResultVO.setRelation(relation.getCode());
        //TODO 发送MQ
        return followResultVO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public FollowResultVO unFollow(Long toUserId, Long topicId, Long fromUserId) {
        return null;
    }

    @Override
    public Map<String, FollowStatusVO> batchGetFollowStatus(Long fromUserId, List<Long> toUserIdList) {
        return null;
    }

    @Override
    public BlacklistResultVO blacklist(BlacklistDTO blacklistDTO, Long fromUserId) {
        return null;
    }

    @Override
    public BlacklistResultVO unBlacklist(Long toUserId, Long fromUserId) {
        return null;
    }

    private FollowRelationEnum getFollowRelationEnum(Long fromUserId, Long toUserId) {
        Set<Long> followIdSet = followCacheService.getAllFollowUserIdSet(fromUserId);
        Set<Long> fansIdSet = followCacheService.getAllFansIdSet(fromUserId);
        Set<Long> blacklistIdSet = followCacheService.getAllBlacklistIdSet(fromUserId);
        Set<Long> haterIdSet = followCacheService.getAllHaterIdSet(fromUserId);
        return getFollowRelationEnum(toUserId, fromUserId, followIdSet, fansIdSet, blacklistIdSet, haterIdSet);
    }

    private List<FollowVO> getFollowVOList(List<Long> idList, Long userId, Boolean isFollow, Long currentUserId) {
        Set<Long> followIdSet = followCacheService.getAllFollowUserIdSet(currentUserId);
        Set<Long> fansIdSet = followCacheService.getAllFansIdSet(currentUserId);
        Set<Long> blacklistIdSet = followCacheService.getAllBlacklistIdSet(currentUserId);
        Set<Long> haterIdSet = followCacheService.getAllHaterIdSet(currentUserId);
        return idList.stream().map(id -> this.getFollowVODetail(id, userId, currentUserId, followIdSet, fansIdSet, blacklistIdSet, haterIdSet, isFollow)).collect(Collectors.toList());
    }

    private FollowVO getFollowVODetail(Long id, Long userId, Long currentUserId, Set<Long> followIdSet, Set<Long> fansIdSet, Set<Long> blacklistIdSet, Set<Long> haterIdSet, boolean isFollow) {
        // 设置用户的信息
        FollowVO followVO = new FollowVO();
        if (isFollow) {
            followVO.setToUserId(id);
            followVO.setFromUserId(userId);
        } else {
            followVO.setFromUserId(id);
            followVO.setToUserId(userId);
        }
        //获取关注关系
        followVO.setRelation(getFollowRelationEnum(id, currentUserId, followIdSet, fansIdSet, blacklistIdSet, haterIdSet).getCode());
        return followVO;
    }

    private FollowRelationEnum getFollowRelationEnum(Long userId, Long fromUserId, Set<Long> followIdSet, Set<Long> fansIdSet, Set<Long> blacklistIdSet, Set<Long> haterIdSet) {
        if (Objects.equals(fromUserId, userId)) {
            //本人
            return FollowRelationEnum.SELF;
        } else if (blacklistIdSet.contains(userId) && haterIdSet.contains(userId)) {
            //互相拉黑
            return FollowRelationEnum.MUTUAL_BLACKLIST;
        } else if (blacklistIdSet.contains(userId)) {
            //已拉黑
            return FollowRelationEnum.BLACKLISTED;
        } else if (haterIdSet.contains(userId)) {
            //已被拉黑
            return FollowRelationEnum.IS_BLACKLISTED;
        } else if (followIdSet.contains(userId) && fansIdSet.contains(userId)) {
            //互相关注
            return FollowRelationEnum.MUTUAL_FOLLOW;
        } else if (followIdSet.contains(userId)) {
            //已关注
            return FollowRelationEnum.FOLLOWED;
        } else if (fansIdSet.contains(userId)) {
            //已被关注
            return FollowRelationEnum.IS_FOLLOWED;
        } else {
            //未关注
            return FollowRelationEnum.NOT_FOLLOW;
        }
    }
}

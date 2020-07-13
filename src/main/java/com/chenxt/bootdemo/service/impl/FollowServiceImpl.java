package com.chenxt.bootdemo.service.impl;

import com.chenxt.bootdemo.dto.BlacklistDTO;
import com.chenxt.bootdemo.dto.FollowDTO;
import com.chenxt.bootdemo.entity.Follow;
import com.chenxt.bootdemo.mapper.FollowMapper;
import com.chenxt.bootdemo.service.IFollowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenxt.bootdemo.vo.BlacklistResultVO;
import com.chenxt.bootdemo.vo.FollowResultVO;
import com.chenxt.bootdemo.vo.FollowStatusVO;
import com.chenxt.bootdemo.vo.FollowVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 关注 服务实现类
 *
 * @author chenxt
 * @date 2020-07-12
 */
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements IFollowService {
    @Override
    public List<FollowVO> getFollowListByTimeline(Long userId, long timeline, long count, Long currentUserId) {
        return null;
    }

    @Override
    public List<FollowVO> getFansListByTimeline(Long userId, long timeline, long count, Long currentUserId) {
        return null;
    }

    @Override
    public FollowResultVO follow(FollowDTO followDTO, Long currentUserId) {
        return null;
    }

    @Override
    public FollowResultVO unFollow(Long toUserId, Long topicId, Long currentUserId) {
        return null;
    }

    @Override
    public Map<String, FollowStatusVO> batchGetFollowStatus(Long fromUserId, List<Long> toUserIdList) {
        return null;
    }

    @Override
    public BlacklistResultVO blacklist(BlacklistDTO blacklistDTO, Long currentUserId) {
        return null;
    }

    @Override
    public BlacklistResultVO unBlacklist(Long toUserId, Long currentUserId) {
        return null;
    }
}

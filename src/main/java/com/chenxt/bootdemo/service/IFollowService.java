package com.chenxt.bootdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenxt.bootdemo.dto.BlacklistDTO;
import com.chenxt.bootdemo.dto.FollowDTO;
import com.chenxt.bootdemo.entity.Follow;
import com.chenxt.bootdemo.vo.BlacklistResultVO;
import com.chenxt.bootdemo.vo.FollowResultVO;
import com.chenxt.bootdemo.vo.FollowStatusVO;
import com.chenxt.bootdemo.vo.FollowVO;

import java.util.List;
import java.util.Map;

/**
 * 关注 服务类
 *
 * @author chenxt
 * @date 2020-07-12
 */
public interface IFollowService extends IService<Follow> {

    List<FollowVO> getFollowListByTimeline(Long userId, long timeline, long count, Long currentUserId);

    List<FollowVO> getFansListByTimeline(Long userId, long timeline, long count, Long currentUserId);

    FollowResultVO follow(FollowDTO followDTO, Long currentUserId);

    FollowResultVO unFollow(Long toUserId, Long currentUserId);

    Map<String, FollowStatusVO> batchGetFollowStatus(Long fromUserId, List<Long> toUserIdList);

    BlacklistResultVO blacklist(BlacklistDTO blacklistDTO, Long currentUserId);

    BlacklistResultVO unBlacklist(Long toUserId, Long currentUserId);
}

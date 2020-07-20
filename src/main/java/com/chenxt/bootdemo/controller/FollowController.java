package com.chenxt.bootdemo.controller;


import com.chenxt.bootdemo.base.security.Token;
import com.chenxt.bootdemo.base.security.TokenParam;
import com.chenxt.bootdemo.base.util.TimelineUtils;
import com.chenxt.bootdemo.base.vo.TimelineVO;
import com.chenxt.bootdemo.dto.BlacklistDTO;
import com.chenxt.bootdemo.dto.FollowDTO;
import com.chenxt.bootdemo.service.IFollowService;
import com.chenxt.bootdemo.vo.BlacklistResultVO;
import com.chenxt.bootdemo.vo.FollowResultVO;
import com.chenxt.bootdemo.vo.FollowStatusVO;
import com.chenxt.bootdemo.vo.FollowVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 关注 前端控制器
 *
 * @author chenxt
 * @date 2020-07-12
 */
@RestController
@RequestMapping("/follow")
public class FollowController {
    @Resource
    private IFollowService followService;

    @ApiOperation(value = "获取关注列表Timeline", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户编号", name = "userId", required = true),
            @ApiImplicitParam(value = "最后游标", name = "timeline", required = true),
            @ApiImplicitParam(value = "请求数据量", name = "count", required = true)
    })
    @GetMapping("/follow/{userId}")
    public TimelineVO<List<FollowVO>> getFollowListByTimeline(@PathVariable Long userId,
                                                              @RequestParam long timeline,
                                                              @RequestParam long count,
                                                              @TokenParam Token token) {
        List<FollowVO> followVOList = followService.getFollowListByTimeline(userId, timeline, count, token.getCurrentUserId());
        //TODO 获取用户信息
        return TimelineUtils.renderTimelineVO(timeline, followVOList, FollowVO::getToUserId);
    }

    @ApiOperation(value = "获取粉丝列表Timeline", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户编号", name = "userId", required = true),
            @ApiImplicitParam(value = "最后游标", name = "timeline", required = true),
            @ApiImplicitParam(value = "请求数据量", name = "count", required = true)
    })
    @GetMapping("/fans/{userId}")
    public TimelineVO<List<FollowVO>> getFansListByTimeline(@PathVariable Long userId,
                                                            @RequestParam long timeline,
                                                            @RequestParam long count,
                                                            @TokenParam Token token) {
        List<FollowVO> followVOList = followService.getFansListByTimeline(userId, timeline, count, token.getCurrentUserId());
        //TODO 获取用户信息
        return TimelineUtils.renderTimelineVO(timeline, followVOList, FollowVO::getFromUserId);
    }

    @ApiOperation(value = "新增关注", produces = "application/json")
    @PostMapping("/follow")
    public FollowResultVO follow(@RequestBody FollowDTO followDTO,
                                 @TokenParam Token token) {
        //TODO 若用户不存在抛出异常
//        UserExistVO userExistVO = userClient.isExist(followDTO.getToUserId());
//        BusinessExceptionCodeEnum.USER_NOT_EXISTS.assertIsTrue(userExistVO.getIsExist);
        return followService.follow(followDTO, token.getCurrentUserId());
    }

    @ApiOperation(value = "取消关注", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "被关注用户编号", name = "toUserId"),
            @ApiImplicitParam(value = "被关注话题编号", name = "topicId")
    })
    @DeleteMapping("/follow")
    public FollowResultVO unfollow(@RequestParam(required = false) Long toUserId,
                                   @TokenParam Token token) {
        return followService.unFollow(toUserId, token.getCurrentUserId());
    }

    @ApiOperation(value = "批量获取是否关注", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "关注用户编号", name = "fromUserId", required = true),
            @ApiImplicitParam(value = "被关注用户编号列表", name = "toUserIdList")
    })
    @GetMapping("/follow/status/batch")
    public Map<String, FollowStatusVO> batchGetFollowStatus(@RequestParam Long fromUserId,
                                                            @RequestParam List<Long> toUserIdList) {
        return followService.batchGetFollowStatus(fromUserId, toUserIdList);
    }

    @ApiOperation(value = "新增拉黑", produces = "application/json")
    @PostMapping("/blacklist")
    public BlacklistResultVO blacklist(@RequestBody BlacklistDTO blacklistDTO,
                                       @TokenParam Token token) {
        //TODO 若用户不存在抛出异常
//        UserExistVO userExistVO = userClient.isExist(blacklistDTO.getToUserId());
//        if (!userExistVO.getIsExist()) {
//            throw new BusinessException(CodeStatusEnum.USER_NOT_EXIST.getCode(), CodeStatusEnum.USER_NOT_EXIST.getMessage());
//        }
        return followService.blacklist(blacklistDTO, token.getCurrentUserId());
    }

    @ApiOperation(value = "取消拉黑", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "被拉黑用户编号", name = "toUserId", required = true)
    })
    @DeleteMapping("/blacklist")
    public BlacklistResultVO cancelBlacklist(@RequestParam Long toUserId,
                                             @TokenParam Token token) {
        return followService.unBlacklist(toUserId, token.getCurrentUserId());
    }
}


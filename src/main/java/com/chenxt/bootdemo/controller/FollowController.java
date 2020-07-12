package com.chenxt.bootdemo.controller;


import com.chenxt.bootdemo.service.IFollowService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
}


package com.chenxt.bootdemo.controller;


import com.alibaba.fastjson.JSON;
import com.chenxt.bootdemo.base.config.RabbitMqConfig;
import com.chenxt.bootdemo.dto.UserLoginDTO;
import com.chenxt.bootdemo.mq.rabbit.sender.RabbitMqSender;
import com.chenxt.bootdemo.service.IUserService;
import com.chenxt.bootdemo.vo.UserVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户 前端控制器
 *
 * @author chenxt
 * @date 2020-07-07
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;
    @Resource
    private RabbitMqSender rabbitMqSender;

    @ApiOperation(value = "登陆", produces = "application/json")
    @PostMapping("/login")
    public UserVO login(@RequestBody UserLoginDTO userLoginDTO) {
        UserVO userVO = userService.login(userLoginDTO);
        rabbitMqSender.send(RabbitMqConfig.USER_LOGIN_EXCHANGE, "bootdemo.user.login.log", JSON.toJSONString(userVO));
        return userVO;
    }
}


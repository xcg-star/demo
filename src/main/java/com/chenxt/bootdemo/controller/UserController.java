package com.chenxt.bootdemo.controller;


import com.alibaba.fastjson.JSON;
import com.chenxt.bootdemo.base.config.RabbitMqConfig;
import com.chenxt.bootdemo.base.security.JwtUtil;
import com.chenxt.bootdemo.base.security.Token;
import com.chenxt.bootdemo.dto.UserDTO;
import com.chenxt.bootdemo.dto.UserLoginDTO;
import com.chenxt.bootdemo.mq.rabbit.sender.RabbitMqSender;
import com.chenxt.bootdemo.service.IUserService;
import com.chenxt.bootdemo.vo.UserVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
    @Resource
    private HttpServletResponse httpServletResponse;

    @ApiOperation(value = "登陆", produces = "application/json")
    @PostMapping("/login")
    public UserVO login(@RequestBody UserLoginDTO userLoginDTO) {
        UserVO userVO = userService.login(userLoginDTO);
        httpServletResponse.setHeader("jwt-token", JwtUtil.createJwt(Token.builder().currentUserId(userVO.getId()).build()));
        rabbitMqSender.send(RabbitMqConfig.USER_LOGIN_EXCHANGE, "bootdemo.user.login.log", JSON.toJSONString(userVO));
        return userVO;
    }

    @ApiOperation(value = "更新用户", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户编号", required = true)
    })
    @PutMapping("/{id}")
    public UserVO updateUser(@PathVariable Long id,
                             @RequestBody UserDTO userDTO) {
        return userService.updateUserById(id, userDTO);
    }
}


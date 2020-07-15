package com.chenxt.bootdemo.controller;


import com.chenxt.bootdemo.dto.UserLoginDTO;
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

    @ApiOperation(value = "登陆", produces = "application/json")
    @PostMapping("/login")
    public UserVO login(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }
}


package com.chenxt.bootdemo.react.controller;

import com.chenxt.bootdemo.service.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户react controller
 *
 * @author chenxt
 * @date 2020/07/22
 */
@RestController
@RequestMapping("/user/react")
public class UserReactController {
    @Resource
    private IUserService userService;

}

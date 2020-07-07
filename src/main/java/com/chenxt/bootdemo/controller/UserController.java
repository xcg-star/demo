package com.chenxt.bootdemo.controller;


import com.chenxt.bootdemo.base.util.ConstellationUtils;
import com.chenxt.bootdemo.base.util.PinyinUtils;
import com.chenxt.bootdemo.dto.UserDTO;
import com.chenxt.bootdemo.entity.User;
import com.chenxt.bootdemo.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

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

    @ApiOperation(value = "新增用户", produces = "application/json")
    @PostMapping("")
    public void addUser(@RequestBody @Valid UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setConstellation(ConstellationUtils.getConstellation(user.getBirthday()));
        user.setNickNamePinyin(PinyinUtils.toPinYin(user.getNickName()));
        user.setBirthday(new Date());
        userService.save(user);
    }
}


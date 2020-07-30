package com.chenxt.bootdemo.controller;


import com.alibaba.fastjson.JSON;
import com.chenxt.bootdemo.base.config.RabbitMqConfig;
import com.chenxt.bootdemo.base.security.JwtUtil;
import com.chenxt.bootdemo.base.security.Token;
import com.chenxt.bootdemo.base.security.TokenParam;
import com.chenxt.bootdemo.dto.UserDTO;
import com.chenxt.bootdemo.dto.UserLoginDTO;
import com.chenxt.bootdemo.mq.rabbit.sender.RabbitMqSender;
import com.chenxt.bootdemo.service.IUserService;
import com.chenxt.bootdemo.vo.UserSearchResultVO;
import com.chenxt.bootdemo.vo.UserVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    @ApiOperation(value = "昵称搜索用户", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nickName", value = "用户昵称"),
            @ApiImplicitParam(name = "page", value = "页码", required = true),
            @ApiImplicitParam(name = "size", value = "每页条数", required = true),
    })
    @GetMapping("/search")
    public List<UserSearchResultVO> search(@RequestParam("page") Integer page,
                                           @RequestParam("size") Integer size,
                                           @RequestParam("nickName") String nickName,
                                           @TokenParam Token token) {
        return userService.searchByNickName(nickName, page, size, token);
    }
}


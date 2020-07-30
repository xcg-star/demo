package com.chenxt.bootdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenxt.bootdemo.base.security.Token;
import com.chenxt.bootdemo.dto.UserDTO;
import com.chenxt.bootdemo.dto.UserLoginDTO;
import com.chenxt.bootdemo.entity.User;
import com.chenxt.bootdemo.vo.UserSearchResultVO;
import com.chenxt.bootdemo.vo.UserVO;

import java.util.List;

/**
 * 用户 服务类
 *
 * @author chenxt
 * @date 2020-07-07
 */
public interface IUserService extends IService<User> {

    /**
     * 登陆
     *
     * @param userLoginDTO 登陆信息
     * @return 登陆用户信息
     */
    UserVO login(UserLoginDTO userLoginDTO);

    /**
     * 更新用户
     *
     * @param userId  用户编号
     * @param userDTO 用户
     * @return 用户VO
     */
    UserVO updateUserById(Long userId, UserDTO userDTO);

    /**
     * 昵称搜索用户列表
     *
     * @param nickName 用户昵称
     * @param page     页码
     * @param size     每页条数
     * @param token    当前用户信息
     * @return 用户列表
     */
    List<UserSearchResultVO> searchByNickName(String nickName, Integer page, Integer size, Token token);
}

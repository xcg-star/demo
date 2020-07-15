package com.chenxt.bootdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenxt.bootdemo.dto.UserLoginDTO;
import com.chenxt.bootdemo.entity.User;
import com.chenxt.bootdemo.vo.UserVO;

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
}

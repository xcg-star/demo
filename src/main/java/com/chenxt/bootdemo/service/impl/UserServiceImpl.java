package com.chenxt.bootdemo.service.impl;

import com.chenxt.bootdemo.entity.User;
import com.chenxt.bootdemo.mapper.UserMapper;
import com.chenxt.bootdemo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户 服务实现类
 *
 * @author chenxt
 * @date 2020-07-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}

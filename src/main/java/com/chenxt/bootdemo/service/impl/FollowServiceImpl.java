package com.chenxt.bootdemo.service.impl;

import com.chenxt.bootdemo.entity.Follow;
import com.chenxt.bootdemo.mapper.FollowMapper;
import com.chenxt.bootdemo.service.IFollowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 关注 服务实现类
 *
 * @author chenxt
 * @date 2020-07-12
 */
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements IFollowService {

}

package com.chenxt.bootdemo.mapper;

import com.chenxt.bootdemo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户 Mapper 接口
 *
 * @author chenxt
 * @date 2020-07-07
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

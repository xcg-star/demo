package com.chenxt.bootdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenxt.bootdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户 Mapper 接口
 *
 * @author chenxt
 * @date 2020-07-07
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据手机号码查询用户
     *
     * @param phone 手机号码
     * @return 用户
     */
    User selectByPhone(@Param("phone") String phone);

    /**
     * 根据电子邮箱查询用户
     *
     * @param email 电子邮箱
     * @return 用户
     */
    User selectByEmail(@Param("email") String email);

    /**
     * 根据昵称查询用户数量
     *
     * @param nickName 昵称
     * @return 用户数量
     */
    Integer countByNickName(@Param("nickName") String nickName);
}

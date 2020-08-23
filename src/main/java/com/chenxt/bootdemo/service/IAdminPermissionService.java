package com.chenxt.bootdemo.service;

import com.chenxt.bootdemo.dto.AdminUserDTO;
import com.chenxt.bootdemo.dto.AdminUserLoginDTO;
import com.chenxt.bootdemo.dto.AdminUserUpdateDTO;
import com.chenxt.bootdemo.dto.AdminUserVerifyCodeDTO;
import com.chenxt.bootdemo.vo.AdminUserVO;

/**
 * 后台管理权限service接口
 *
 * @author chenxt
 * @date 2020/08/16
 */
public interface IAdminPermissionService {

    /**
     * 账号密码登陆验证
     *
     * @param adminUserLoginDTO 登陆信息
     * @return 验证结果 boolean
     */
    Boolean adminLogin(AdminUserLoginDTO adminUserLoginDTO);

    /**
     * 验证码登陆验证
     *
     * @param adminUserVerifyCodeDTO 验证码信息
     * @return 验证结果 admin user vo
     */
    AdminUserVO authorize(AdminUserVerifyCodeDTO adminUserVerifyCodeDTO);

    /**
     * 重置密钥
     *
     * @param userId 用户id
     * @return the admin user
     */
    void resetSecret(Long userId);

    /**
     * 添加用户
     *
     * @param adminUserDTO 用户
     * @return 添加结果 boolean
     */
    Boolean addUser(AdminUserDTO adminUserDTO);

    /**
     * 查询用户名是否存在
     *
     * @param name 用户名
     * @return 是否存在 boolean
     */
    Boolean isUserNameExist(String name);

    /**
     * 查询账号是否存在
     *
     * @param account 账号
     * @return 是否存在 boolean
     */
    Boolean isUserAccountExist(String account);

    /**
     * 更新用户信息
     *
     * @param adminUserUpdateDTO 用户信息
     */
    void updateUser(AdminUserUpdateDTO adminUserUpdateDTO);
}

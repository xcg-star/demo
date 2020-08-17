package com.chenxt.bootdemo.service;

import com.chenxt.bootdemo.dto.AdminUserLoginDTO;
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
}

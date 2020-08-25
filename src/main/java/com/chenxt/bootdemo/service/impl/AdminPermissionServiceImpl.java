package com.chenxt.bootdemo.service.impl;

import com.chenxt.bootdemo.base.security.Token;
import com.chenxt.bootdemo.dto.*;
import com.chenxt.bootdemo.service.IAdminPermissionService;
import com.chenxt.bootdemo.vo.AdminUserVO;
import org.springframework.stereotype.Service;

/**
 * 后台管理权限service接口
 *
 * @author chenxt
 * @date 2020/08/03
 */
@Service
public class AdminPermissionServiceImpl implements IAdminPermissionService {
    @Override
    public Boolean adminLogin(AdminUserLoginDTO adminUserLoginDTO) {
        return null;
    }

    @Override
    public AdminUserVO authorize(AdminUserVerifyCodeDTO adminUserVerifyCodeDTO) {
        return null;
    }

    @Override
    public void resetSecret(Long userId) {

    }

    @Override
    public Boolean addUser(AdminUserDTO adminUserDTO) {
        return null;
    }

    @Override
    public Boolean isUserNameExist(String name) {
        return null;
    }

    @Override
    public Boolean isUserAccountExist(String account) {
        return null;
    }

    @Override
    public void updateUser(AdminUserUpdateDTO adminUserUpdateDTO) {

    }

    @Override
    public void updatePassword(AdminPasswordUpdateDTO adminPasswordUpdateDTO, Token token) {

    }

    @Override
    public void deleteUser(Long id) {

    }
}

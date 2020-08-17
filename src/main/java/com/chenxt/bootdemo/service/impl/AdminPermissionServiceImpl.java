package com.chenxt.bootdemo.service.impl;

import com.chenxt.bootdemo.dto.AdminUserLoginDTO;
import com.chenxt.bootdemo.dto.AdminUserVerifyCodeDTO;
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
}

package com.chenxt.bootdemo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chenxt.bootdemo.base.security.Token;
import com.chenxt.bootdemo.dto.*;
import com.chenxt.bootdemo.service.IAdminPermissionService;
import com.chenxt.bootdemo.vo.*;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public IPage<AdminUserListVO> getUserList(AdminUserListDTO adminUserListDTO) {
        return null;
    }

    @Override
    public Long addMenu(AdminMenuDTO adminMenuDTO) {
        return null;
    }

    @Override
    public void updateMenu(AdminMenuUpdateDTO adminMenuUpdateDTO) {

    }

    @Override
    public void deleteMenu(Long id) {

    }

    @Override
    public List<AdminMenuVO> getMenuList() {
        return null;
    }

    @Override
    public Long addGroup(AdminGroupDTO adminGroupDTO) {
        return null;
    }

    @Override
    public void updateGroup(AdminGroupUpdateDTO adminGroupUpdateDTO) {

    }

    @Override
    public void deleteGroup(Long id) {

    }

    @Override
    public IPage<AdminGroupListVO> getGroupList(AdminGroupListDTO adminGroupListDTO) {
        return null;
    }

    @Override
    public void updateUserGroup(Long userId, AdminUserGroupDTO adminUserGroupDTO) {

    }

    @Override
    public void deleteUserGroup(Long userId, Long groupId) {

    }

    @Override
    public IPage<AdminUserGroupVO> getUserGroupList(Long userId, Integer page, Integer size) {
        return null;
    }

    @Override
    public List<AdminUserGroupVO> getUserAllGroupList(Long userId) {
        return null;
    }

    @Override
    public void updateGroupUser(Long groupId, AdminGroupUserDTO adminGroupUserDTO) {

    }

    @Override
    public void deleteGroupUser(Long groupId, Long userId) {

    }

    @Override
    public IPage<AdminGroupUserVO> getGroupUserList(Long groupId, Integer page, Integer size) {
        return null;
    }

    @Override
    public IPage<AdminUserPermissionVO> getUserPermissionList(Long userId, Integer page, Integer size) {
        return null;
    }

    @Override
    public IPage<AdminUserPermissionExtendVO> getUserPermissionExtendList(Long userId, Integer page, Integer size, Long groupId) {
        return null;
    }

    @Override
    public List<AdminUserPermissionAllVO> getUserPermissionAllList(Long userId) {
        return null;
    }

    @Override
    public void updateUserPermission(Long userId, AdminUserPermissionDTO adminUserPermissionDTO) {

    }

    @Override
    public void deleteUserPermission(Long userId, Long permissionId) {

    }

    @Override
    public IPage<AdminGroupPermissionVO> getGroupPermissionList(Long groupId, Integer page, Integer size) {
        return null;
    }

    @Override
    public void updateGroupPermission(Long groupId, AdminGroupPermissionDTO adminGroupPermissionDTO) {

    }

    @Override
    public void deleteGroupPermission(Long groupId, Long permissionId) {

    }

    @Override
    public AdminUserListVO getUserDetail(Long userId) {
        return null;
    }

    @Override
    public AdminGroupListVO getGroupDetail(Long groupId) {
        return null;
    }

    @Override
    public List<AdminPermissionVO> getPermissionList() {
        return null;
    }

    @Override
    public IPage<AdminUserListVO> getAdministratorList(Integer page, Integer size, String name) {
        return null;
    }

    @Override
    public void addAdministrator(Long userId) {

    }

    @Override
    public void deleteAdministrator(Long userId) {

    }

    @Override
    public void updateAdministratorStatus(Long userId, AdminEnableDTO adminEnableDTO) {

    }

    @Override
    public List<AdminUserListVO> getCommonUserList(String userName) {
        return null;
    }

    @Override
    public List<AdminUserPermissionForControlVO> getUserPermissionForControlList(Token token) {
        return null;
    }

    @Override
    public List<AdminUserListVO> getGroupUserDropDownList(Long groupId, String name, Token token) {
        return null;
    }

    @Override
    public List<AdminGroupListVO> getUserGroupDropDownList(Long userId, String name) {
        return null;
    }

    @Override
    public AdminUserVO getAdminUser(Long adminUserId) {
        return null;
    }
}

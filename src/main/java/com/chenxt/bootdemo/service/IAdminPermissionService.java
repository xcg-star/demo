package com.chenxt.bootdemo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chenxt.bootdemo.base.security.Token;
import com.chenxt.bootdemo.dto.*;
import com.chenxt.bootdemo.entity.AdminUser;
import com.chenxt.bootdemo.vo.*;

import java.util.List;

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
    AdminUser resetSecret(Long userId);

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

    /**
     * 更新用户密码
     *
     * @param adminPasswordUpdateDTO 密码信息
     * @param token                  用户信息
     */
    void updatePassword(AdminPasswordUpdateDTO adminPasswordUpdateDTO, Token token);

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    void deleteUser(Long id);

    /**
     * 获取用户列表
     *
     * @param adminUserListDTO 条件
     * @return 用户列表 user list
     */
    IPage<AdminUserListVO> getUserList(AdminUserListDTO adminUserListDTO);

    /**
     * 添加菜单
     *
     * @param adminMenuDTO 菜单
     * @return 菜单id long
     */
    Long addMenu(AdminMenuDTO adminMenuDTO);

    /**
     * 更新菜单
     *
     * @param adminMenuUpdateDTO 菜单
     */
    void updateMenu(AdminMenuUpdateDTO adminMenuUpdateDTO);

    /**
     * 删除菜单
     *
     * @param id 菜单id
     */
    void deleteMenu(Long id);

    /**
     * 获取菜单列表
     *
     * @return 菜单列表 menu list
     */
    List<AdminMenuVO> getMenuList();

    /**
     * 添加用户组
     *
     * @param adminGroupDTO 用户组
     * @return 用户组id long
     */
    Long addGroup(AdminGroupDTO adminGroupDTO);

    /**
     * 更新用户组
     *
     * @param adminGroupUpdateDTO 用户组
     */
    void updateGroup(AdminGroupUpdateDTO adminGroupUpdateDTO);

    /**
     * 删除用户组
     *
     * @param id 用户组id
     */
    void deleteGroup(Long id);

    /**
     * 获取用户组分页列表
     *
     * @param adminGroupListDTO 查询条件
     * @return 用户组分页列表 group list
     */
    IPage<AdminGroupListVO> getGroupList(AdminGroupListDTO adminGroupListDTO);

    /**
     * 全量更新用户的用户组
     *
     * @param userId            用户id
     * @param adminUserGroupDTO 用户的用户组
     */
    void updateUserGroup(Long userId, AdminUserGroupDTO adminUserGroupDTO);

    /**
     * 删除用户的用户组
     *
     * @param userId  用户id
     * @param groupId 用户组id
     */
    void deleteUserGroup(Long userId, Long groupId);

    /**
     * 获取用户的用户组分页列表
     *
     * @param userId 用户id
     * @param page   页码
     * @param size   每页条数
     * @return 用户的用户组分页列表 user group list
     */
    IPage<AdminUserGroupVO> getUserGroupList(Long userId, Integer page, Integer size);

    /**
     * 获取用户的全部用户组列表
     *
     * @param userId 用户id
     * @return 用户的全部用户组列表 user all group list
     */
    List<AdminUserGroupVO> getUserAllGroupList(Long userId);

    /**
     * 全量更新用户组的用户
     *
     * @param groupId           用户组id
     * @param adminGroupUserDTO 用户组的用户
     */
    void updateGroupUser(Long groupId, AdminGroupUserDTO adminGroupUserDTO);

    /**
     * 删除用户组的用户
     *
     * @param groupId 用户组id
     * @param userId  用户id
     */
    void deleteGroupUser(Long groupId, Long userId);

    /**
     * 获取用户组的用户分页列表
     *
     * @param groupId 用户组id
     * @param page    页码
     * @param size    每页条数
     * @return 用户组的用户分页列表 group user list
     */
    IPage<AdminGroupUserVO> getGroupUserList(Long groupId, Integer page, Integer size);

    /**
     * 获取用户的权限分页列表
     *
     * @param userId 用户id
     * @param page   页码
     * @param size   每页条数
     * @return 用户的权限分页列表 user permission list
     */
    IPage<AdminUserPermissionVO> getUserPermissionList(Long userId, Integer page, Integer size);

    /**
     * 获取用户的继承权限分页列表
     *
     * @param userId  用户id
     * @param page    页码
     * @param size    每页条数
     * @param groupId 用户组id
     * @return 用户的权限分页列表 user permission extend list
     */
    IPage<AdminUserPermissionExtendVO> getUserPermissionExtendList(Long userId, Integer page, Integer size, Long groupId);

    /**
     * 获取用户的全部权限列表
     *
     * @param userId 用户id
     * @return 用户的全部权限列表 user permission all list
     */
    List<AdminUserPermissionAllVO> getUserPermissionAllList(Long userId);

    /**
     * 全量更新用户的权限
     *
     * @param userId                 用户id
     * @param adminUserPermissionDTO 权限列表
     */
    void updateUserPermission(Long userId, AdminUserPermissionDTO adminUserPermissionDTO);

    /**
     * 删除用户的权限
     *
     * @param userId       用户id
     * @param permissionId 权限id
     */
    void deleteUserPermission(Long userId, Long permissionId);

    /**
     * 获取用户组的权限分页列表
     *
     * @param groupId 用户组id
     * @param page    页码
     * @param size    每页条数
     * @return 用户组的权限分页列表 group permission list
     */
    IPage<AdminGroupPermissionVO> getGroupPermissionList(Long groupId, Integer page, Integer size);

    /**
     * 全量更新用户组的权限
     *
     * @param groupId                 用户组id
     * @param adminGroupPermissionDTO 权限列表
     */
    void updateGroupPermission(Long groupId, AdminGroupPermissionDTO adminGroupPermissionDTO);

    /**
     * 删除用户组的权限
     *
     * @param groupId      用户组id
     * @param permissionId 权限id
     */
    void deleteGroupPermission(Long groupId, Long permissionId);

    /**
     * 获取用户详情
     *
     * @param userId 用户id
     * @return 用户详情 user detail
     */
    AdminUserListVO getUserDetail(Long userId);

    /**
     * 获取用户组详情
     *
     * @param groupId 用户组id
     * @return 用户组详情 group detail
     */
    AdminGroupListVO getGroupDetail(Long groupId);

    /**
     * 获取全部权限列表
     *
     * @return 全部权限列表 permission list
     */
    List<AdminPermissionVO> getPermissionList();

    /**
     * 获取管理员列表
     *
     * @param page 页码
     * @param size 每页条数
     * @param name 用户名
     * @return 管理员列表 administrator list
     */
    IPage<AdminUserListVO> getAdministratorList(Integer page, Integer size, String name);

    /**
     * 添加管理员
     *
     * @param userId 用户id
     */
    void addAdministrator(Long userId);

    /**
     * 删除管理员
     *
     * @param userId 用户id
     */
    void deleteAdministrator(Long userId);

    /**
     * 更新管理员状态(启用/禁用)
     *
     * @param userId         用户id
     * @param adminEnableDTO 是否启用
     */
    void updateAdministratorStatus(Long userId, AdminEnableDTO adminEnableDTO);

    /**
     * 获取普通用户列表(添加管理员下拉列表用)
     *
     * @param userName 用户名
     * @return 普通用户列表 common user list
     */
    List<AdminUserListVO> getCommonUserList(String userName);

    /**
     * 获取当前用户的权限列表
     *
     * @param token 用户信息
     * @return 当前用户的权限列表 user permission for control list
     */
    List<AdminUserPermissionForControlVO> getUserPermissionForControlList(Token token);

    /**
     * 获取用户组的用户下拉列表
     *
     * @param groupId 用户组id
     * @param name    用户名
     * @param token   用户信息
     * @return 用户组的用户下拉列表 group user drop down list
     */
    List<AdminUserListVO> getGroupUserDropDownList(Long groupId, String name, Token token);

    /**
     * 获取用户的用户组下拉列表
     *
     * @param userId 用户id
     * @param name   用户组名
     * @return 用户的用户组下拉列表 user group drop down list
     */
    List<AdminGroupListVO> getUserGroupDropDownList(Long userId, String name);

    /**
     * 获取后台管理用户
     *
     * @param adminUserId 后台管理用户id
     * @return 后台管理用户 admin user
     */
    AdminUserVO getAdminUser(Long adminUserId);
}

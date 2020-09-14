package com.chenxt.bootdemo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chenxt.bootdemo.base.expection.enumeration.BusinessExceptionCodeEnum;
import com.chenxt.bootdemo.base.security.Token;
import com.chenxt.bootdemo.base.third.auth2.GoogleAuthenticatorUtils;
import com.chenxt.bootdemo.base.util.Md5Utils;
import com.chenxt.bootdemo.base.util.ValidateUtils;
import com.chenxt.bootdemo.dto.*;
import com.chenxt.bootdemo.entity.AdminUser;
import com.chenxt.bootdemo.mapper.AdminGroupUserLinkMapper;
import com.chenxt.bootdemo.mapper.AdminPermissionLinkMapper;
import com.chenxt.bootdemo.mapper.AdminUserMapper;
import com.chenxt.bootdemo.service.IAdminPermissionService;
import com.chenxt.bootdemo.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台管理权限service接口
 *
 * @author chenxt
 * @date 2020/08/03
 */
@Service
public class AdminPermissionServiceImpl implements IAdminPermissionService {
    @Resource
    private AdminUserMapper adminUserMapper;
    @Resource
    private AdminGroupUserLinkMapper adminGroupUserLinkMapper;
    @Resource
    private AdminPermissionLinkMapper adminPermissionLinkMapper;

    private static final String SECRET_QR_CODE_ISSUER = "http://www.chenxt.com";

    @Override
    public Boolean adminLogin(AdminUserLoginDTO adminUserLoginDTO) {
        String account = adminUserLoginDTO.getAccount();
        String password = adminUserLoginDTO.getPassword();
        BusinessExceptionCodeEnum.INVALID_PARAMETER.assertIsFalse(ValidateUtils.isOneEmpty(account, password));
        AdminUser adminUser = adminUserMapper.selectByAccount(account);
        BusinessExceptionCodeEnum.ADMIN_USER_ACCOUNT_NOT_EXIST.assertNotNull(adminUser);
        //密码错误
        BusinessExceptionCodeEnum.ADMIN_USER_PASSWORD_WRONG.assertIsTrue(Md5Utils.verifyHex(adminUser.getPassword(), password));
        return true;
    }

    @Override
    public AdminUserVO authorize(AdminUserVerifyCodeDTO adminUserVerifyCodeDTO) {
        String account = adminUserVerifyCodeDTO.getAccount();
        String verifyCode = adminUserVerifyCodeDTO.getVerifyCode();
        AdminUser adminUser = adminUserMapper.selectByAccount(account);
        //账号不存在
        BusinessExceptionCodeEnum.ADMIN_USER_ACCOUNT_NOT_EXIST.assertNotNull(adminUser);
        long code = Long.parseLong(verifyCode);
//        BusinessExceptionCodeEnum.ADMIN_USER_VERIFYCODE_WRONG.assertIsTrue(GoogleAuthenticatorUtils.check_code(adminUser.getSecret(), code, System.currentTimeMillis()));
        AdminUserVO adminUserVO = new AdminUserVO();
        BeanUtils.copyProperties(adminUser, adminUserVO);
        //TODO
//        adminUserVO.setCdnUrl(cdnUrl);
//        adminUserVO.setCdnUploadUrl(cdnUploadUrl);
        //设置菜单权限列表
//        adminUserVO.setPermissionList(getPermissionForControlList(adminUser.getId(), adminUser.getType(), adminUser.getIsAdminEnable()));
        return adminUserVO;
    }

    @Override
    public AdminUser resetSecret(Long userId) {
        AdminUser adminUser = new AdminUser();
        adminUser.setId(userId);
        adminUser.setSecret(GoogleAuthenticatorUtils.getRandomSecretKey());
        adminUser.setSecretQrCode(GoogleAuthenticatorUtils.getGoogleAuthenticatorBarCode(adminUser.getSecret(), adminUser.getAccount(), SECRET_QR_CODE_ISSUER));
        adminUserMapper.updateById(adminUser);
        return adminUser;
    }

    @Override
    public Boolean addUser(AdminUserDTO adminUserDTO) {
        BusinessExceptionCodeEnum.INVALID_PARAMETER.assertIsFalse(ValidateUtils.isOneEmpty(adminUserDTO.getName(), adminUserDTO.getAccount(), adminUserDTO.getPassword()));
        //账号已存在
        BusinessExceptionCodeEnum.ADMIN_USER_ACCOUNT_EXIST.assertIsNull(adminUserMapper.selectByAccount(adminUserDTO.getAccount()));
        //用户名已存在
        BusinessExceptionCodeEnum.ADMIN_USER_NAME_EXIST.assertIsNull(adminUserMapper.selectByName(adminUserDTO.getName()));
        AdminUser adminUser = new AdminUser();
        BeanUtils.copyProperties(adminUserDTO, adminUser);
        //密码使用Md5加密
        adminUser.setPassword(Md5Utils.encodeHex(adminUser.getPassword()));
        //设置密钥和密钥二维码
        adminUser.setSecret(GoogleAuthenticatorUtils.getRandomSecretKey());
        adminUser.setSecretQrCode(GoogleAuthenticatorUtils.getGoogleAuthenticatorBarCode(adminUser.getSecret(), adminUser.getAccount(), SECRET_QR_CODE_ISSUER));
        adminUserMapper.insert(adminUser);
        return true;
    }

    @Override
    public Boolean isUserNameExist(String name) {
        return adminUserMapper.selectByName(name) != null;
    }

    @Override
    public Boolean isUserAccountExist(String account) {
        return adminUserMapper.selectByAccount(account) != null;
    }

    @Override
    public void updateUser(AdminUserUpdateDTO adminUserUpdateDTO) {
        AdminUser adminUser = adminUserMapper.selectByName(adminUserUpdateDTO.getName());
        //用户名已存在
        BusinessExceptionCodeEnum.ADMIN_USER_NAME_EXIST.assertIsTrue(adminUser == null || adminUser.getId().equals(adminUserUpdateDTO.getId()));
        adminUser = new AdminUser();
        BeanUtils.copyProperties(adminUserUpdateDTO, adminUser);
        if (adminUser.getPassword() != null) {
            adminUser.setPassword(Md5Utils.encodeHex(adminUser.getPassword()));
        }
        adminUserMapper.updateById(adminUser);
    }

    @Override
    public void updatePassword(AdminPasswordUpdateDTO adminPasswordUpdateDTO, Token token) {
        Long currentUserId = token.getCurrentUserId();
        AdminUser adminUser = adminUserMapper.selectById(currentUserId);
        //用户不存在
        BusinessExceptionCodeEnum.ADMIN_USER_NOT_EXIST.assertNotNull(adminUser);
        //密码错误
        BusinessExceptionCodeEnum.ADMIN_USER_PASSWORD_WRONG.assertIsTrue(Md5Utils.verifyHex(adminUser.getPassword(), adminPasswordUpdateDTO.getOldPassword()));
        adminUser = new AdminUser();
        adminUser.setId(currentUserId);
        adminUser.setPassword(Md5Utils.encodeHex(adminPasswordUpdateDTO.getNewPassword()));
        adminUserMapper.updateById(adminUser);
    }

    @Override
    public void deleteUser(Long id) {
        adminUserMapper.deleteById(id);
        adminGroupUserLinkMapper.deleteByUserId(id);
        adminPermissionLinkMapper.deleteByUserId(id);
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

package com.chenxt.bootdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenxt.bootdemo.entity.AdminPermissionLink;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台管理权限与用户和用户组的关系 Mapper 接口
 *
 * @author chenxt
 * @date 2020-08-05
 */
@Mapper
public interface AdminPermissionLinkMapper extends BaseMapper<AdminPermissionLink> {

    /**
     * 分页查询用户的权限关系列表
     *
     * @param adminPermissionLinkPage 分页条件
     * @param userId                  用户id
     * @return 查询结果
     */
    IPage<AdminPermissionLink> selectPageVoByUserId(Page<AdminPermissionLink> adminPermissionLinkPage, @Param("userId") Long userId);


    /**
     * 分页查询用户的继承权限关系列表
     *
     * @param adminPermissionLinkPage 分页条件
     * @param userId                  用户id
     * @param groupId                 用户组id
     * @return 查询结果
     */
    IPage<AdminPermissionLink> selectExtendPageVoByUserId(Page<AdminPermissionLink> adminPermissionLinkPage,
                                                          @Param("userId") Long userId,
                                                          @Param("groupId") Long groupId);

    /**
     * 分页查询用户组的权限关系列表
     *
     * @param adminPermissionLinkPage 分页条件
     * @param groupId                 用户组id
     * @return 查询结果
     */
    IPage<AdminPermissionLink> selectPageVoByGroupId(Page<AdminPermissionLink> adminPermissionLinkPage, @Param("groupId") Long groupId);

    /**
     * 查询用户的全部权限关系
     *
     * @param userId 用户id
     * @return 用户的全部权限关系
     */
    List<AdminPermissionLink> selectListByUserId(@Param("userId") Long userId);

    /**
     * 查询用户的全部权限(包含继承)
     *
     * @param userId 用户id
     * @return 用户的全部权限(包含继承)
     */
    List<AdminPermissionLink> selectPermissionIdListWithExtendByUserId(@Param("userId") Long userId);

    /**
     * 查询用户的继承权限id列表
     *
     * @param userId 用户id
     * @return 用户的全部权限(包含继承)
     */
    List<Long> selectPermissionIdListOnlyWithExtendByUserId(@Param("userId") Long userId);

    /**
     * 查询用户组的全部权限关系
     *
     * @param groupId 用户组id
     * @return 用户组的全部权限关系
     */
    List<AdminPermissionLink> selectListByGroupId(@Param("groupId") Long groupId);

    /**
     * 删除用户的不在permissionIdList中的权限
     *
     * @param userId           用户id
     * @param permissionIdList 权限id列表
     */
    void deleteByUserIdAndNotInPermissionIdList(@Param("userId") Long userId,
                                                @Param("permissionIdList") List<Long> permissionIdList);

    /**
     * 删除用户组的不在permissionIdList中的权限
     *
     * @param groupId          用户组id
     * @param permissionIdList 权限id列表
     */
    void deleteByGroupIdAndNotInPermissionIdList(@Param("groupId") Long groupId,
                                                 @Param("permissionIdList") List<Long> permissionIdList);

    /**
     * 根据权限id删除关系
     *
     * @param permissionId 权限id
     */
    void deleteByPermissionId(@Param("permissionId") Long permissionId);

    /**
     * 根据用户id删除关系
     *
     * @param userId 用户id
     */
    void deleteByUserId(@Param("userId") Long userId);

    /**
     * 根据用户组id删除关系
     *
     * @param groupId 用户组id
     */
    void deleteByGroupId(@Param("groupId") Long groupId);
}

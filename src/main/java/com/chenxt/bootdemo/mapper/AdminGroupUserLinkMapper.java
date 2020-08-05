package com.chenxt.bootdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenxt.bootdemo.entity.AdminGroupUserLink;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台管理用户组与用户关系 Mapper 接口
 *
 * @author chenxt
 * @date 2020-08-05
 */
public interface AdminGroupUserLinkMapper extends BaseMapper<AdminGroupUserLink> {

    /**
     * 根据用户组id查询用户组与用户关系列表
     *
     * @param groupId 用户组id
     * @return 用户组与用户关系列表
     */
    List<AdminGroupUserLink> selectByGroupId(@Param("groupId") Long groupId);

    /**
     * 全量新增用户的用户组，若存在则跳过(groupIdList不允许为空)
     *
     * @param groupIdList 用户组id列表
     * @param userId      用户id
     */
    void insertByGroupIdListAndUserIdOnDuplicateDoNoting(@Param("groupIdList") List<Long> groupIdList,
                                                         @Param("userId") Long userId);

    /**
     * 全量新增用户组的用户，若存在则跳过(userIdList不允许为空)
     *
     * @param groupId    用户组id
     * @param userIdList 用户id列表
     */
    void insertByGroupIdAndUserIdListOnDuplicateDoNoting(@Param("groupId") Long groupId,
                                                         @Param("userIdList") List<Long> userIdList);

    /**
     * 分页查询用户的用户组
     *
     * @param adminGroupUserLinkPage 分页条件
     * @param userId                 用户id
     * @return 查询结果
     */
    IPage<AdminGroupUserLink> selectPageVoByUserId(Page<AdminGroupUserLink> adminGroupUserLinkPage, @Param("userId") Long userId);

    /**
     * 分页查询用户组的用户
     *
     * @param adminGroupUserLinkPage 分页条件
     * @param groupId                用户组id
     * @return 查询结果
     */
    IPage<AdminGroupUserLink> selectPageVoByGroupId(Page<AdminGroupUserLink> adminGroupUserLinkPage, @Param("groupId") Long groupId);

    /**
     * 删除用户的不在groupIdList中的用户组
     *
     * @param groupIdList 用户组id列表
     * @param userId      用户id
     */
    void deleteByUserIdAndNotInGroupIdList(@Param("groupIdList") List<Long> groupIdList,
                                           @Param("userId") Long userId);

    /**
     * 删除用户组的不在userIdList中的用户
     *
     * @param groupId    用户组id
     * @param userIdList 用户id列表
     */
    void deleteByGroupIdAndNotInUserIdList(@Param("groupId") Long groupId,
                                           @Param("userIdList") List<Long> userIdList);

    /**
     * 查询用户的全部用户组关系
     *
     * @param userId 用户id
     * @return 用户的全部用户组关系
     */
    List<AdminGroupUserLink> selectByUserId(@Param("userId") Long userId);

    /**
     * 根据用户组id查询用户数量
     *
     * @param groupId 用户组id
     * @return 用户数量
     */
    Integer countByGroupId(@Param("groupId") Long groupId);

    /**
     * 根据用户id删除关系
     *
     * @param userId 用户id
     */
    void deleteByUserId(@Param("userId") Long userId);
}

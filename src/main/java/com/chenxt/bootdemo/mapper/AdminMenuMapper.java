package com.chenxt.bootdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenxt.bootdemo.entity.AdminMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台管理菜单 Mapper 接口
 *
 * @author chenxt
 * @date 2020-08-05
 */
public interface AdminMenuMapper extends BaseMapper<AdminMenu> {

    /**
     * 查询是否有子级
     *
     * @param id 菜单id
     * @return 是否有子级
     */
    boolean hasChildren(@Param("id") Long id);

    /**
     * 排序查询父菜单列表
     *
     * @param mustEnable 是否必须启用
     * @return 父菜单列表
     */
    List<AdminMenu> selectParentListOrder(@Param("mustEnable") Boolean mustEnable);

    /**
     * 排序查询子菜单列表
     *
     * @param mustEnable 是否必须启用
     * @return 子菜单列表
     */
    List<AdminMenu> selectChildrenListOrder(@Param("mustEnable") Boolean mustEnable);

    /**
     * 根据名称和父级id查询菜单
     *
     * @param name     菜单名称
     * @param parentId 父级id
     * @return 菜单
     */
    AdminMenu selectByNameAndParentId(@Param("name") String name, @Param("parentId") Long parentId);
}

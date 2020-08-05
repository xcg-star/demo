package com.chenxt.bootdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenxt.bootdemo.entity.AdminPermission;
import org.apache.ibatis.annotations.Param;

/**
 * 后台管理权限 Mapper 接口
 *
 * @author chenxt
 * @date 2020-08-05
 */
public interface AdminPermissionMapper extends BaseMapper<AdminPermission> {
    /**
     * 根据菜单id查询权限
     *
     * @param menuId 菜单id
     * @return 权限
     */
    AdminPermission selectByMenuId(@Param("menuId") Long menuId);
}

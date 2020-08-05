package com.chenxt.bootdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenxt.bootdemo.entity.AdminGroup;
import org.apache.commons.beanutils.BeanMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台管理用户组 Mapper 接口
 *
 * @author chenxt
 * @date 2020-08-05
 */
public interface AdminGroupMapper extends BaseMapper<AdminGroup> {

    /**
     * 根据名称查询用户组
     *
     * @param name 名称
     * @return 用户组
     */
    AdminGroup selectByName(@Param("name") String name);

    /**
     * 分页查询
     *
     * @param adminGroupPage 分页条件
     * @param queryParam     查询条件
     * @return 分页列表
     */
    IPage<AdminGroup> selectPageVo(Page<AdminGroup> adminGroupPage, @Param("queryParam") BeanMap queryParam);

    /**
     * 根据用户组名查询用户组列表
     *
     * @param name 用户组名
     * @return 用户组列表
     */
    List<AdminGroup> selectListByName(@Param("name") String name);
}

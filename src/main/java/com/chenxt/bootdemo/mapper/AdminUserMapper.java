package com.chenxt.bootdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenxt.bootdemo.entity.AdminUser;
import org.apache.commons.beanutils.BeanMap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台管理用户新版 Mapper 接口
 *
 * @author chenxt
 * @date 2020-08-05
 */
@Mapper
public interface AdminUserMapper extends BaseMapper<AdminUser> {

    /**
     * 根据账号查询用户
     *
     * @param account 账号
     * @return 用户
     */
    AdminUser selectByAccount(@Param("account") String account);

    /**
     * 根据用户名查询用户
     *
     * @param name 用户名
     * @return 用户
     */
    AdminUser selectByName(@Param("name") String name);

    /**
     * 根据用户名查询管理员
     *
     * @param userPage 用户名
     * @param name     用户名
     * @return 管理员
     */
    IPage<AdminUser> selectAdministratorByName(Page<AdminUser> userPage, @Param("name") String name);

    /**
     * 分页查询
     *
     * @param userPage   分页条件
     * @param queryParam 查询条件
     * @return 用户列表
     */
    IPage<AdminUser> selectPageVo(Page<AdminUser> userPage, @Param("queryParam") BeanMap queryParam);

    /**
     * 根据用户名查全部用户列表
     *
     * @param name 用户名
     * @return 用户列表
     */
    List<AdminUser> selectUserListByName(@Param("name") String name);

    /**
     * 根据用户名查普通用户列表
     *
     * @param name 用户名
     * @return 用户列表
     */
    List<AdminUser> selectCommonUserListByName(@Param("name") String name);
}

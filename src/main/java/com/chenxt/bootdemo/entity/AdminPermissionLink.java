package com.chenxt.bootdemo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * 后台管理权限与用户和用户组的关系表
 *
 * @author chenxt
 * @date 2020-08-02
 */
@Data
public class AdminPermissionLink implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 编号 - 自增
     */
    @TableId(type = AUTO)
    private Long id;

    /**
     * 权限id(admin_permission.id)
     */
    private Long adminPermissionId;

    /**
     * 用户id(admin_user.id)
     */
    private Long adminUserId;

    /**
     * 用户组id(admin_group.id)
     */
    private Long adminGroupId;

    /**
     * 页面下所有按钮状态(true:显示,false:隐藏)
     */
    private Boolean isButtonEnable;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE, update = "now()")
    private LocalDateTime updatedAt;

}
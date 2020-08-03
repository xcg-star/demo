package com.chenxt.bootdemo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * 后台管理用户组与用户关系表
 *
 * @author chenxt
 * @date 2020-08-02
 */
@Data
public class AdminGroupUserLink implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 编号 - 自增
     */
    @TableId(type = AUTO)
    private Long id;

    /**
     * 用户id(admin_user.id)
     */
    private Long adminUserId;

    /**
     * 用户组id(admin_group.id)
     */
    private Long adminGroupId;

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
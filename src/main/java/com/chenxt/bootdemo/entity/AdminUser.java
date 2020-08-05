package com.chenxt.bootdemo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * 后台管理用户新版表
 *
 * @date 2020-08-04
 */
@Data
public class AdminUser implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 编号 - 自增
     */
    @TableId(type = AUTO)
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 密钥
     */
    private String secret;

    /**
     * 密钥二维码
     */
    private String secretQrCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态(true:正常,false:封禁)
     */
    private Boolean isEnable;

    /**
     * 管理员状态(true:启用,false:禁用)
     */
    private Boolean isAdminEnable;

    /**
     * 类型(3:超级管理员,2:管理员,1:普通用户)
     */
    private Integer type;

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
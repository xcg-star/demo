package com.chenxt.bootdemo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * 后台管理用户组表
 *
 * @author chenxt
 * @date 2020-08-01
 */
@Data
public class AdminGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 编号 - 自增
     */
    @TableId(type = AUTO)
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态(true:启用,false:禁用)
     */
    private Boolean isEnable;

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
package com.chenxt.bootdemo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * 后台管理菜单表
 *
 * @author chenxt
 * @date 2020-08-02
 */
@Data
public class AdminMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 编号 - 自增
     */
    @TableId(type = AUTO)
    private Long id;

    /**
     * 父级菜单id,若为一级菜单则为空(admin_menu.id)
     */
    private Long parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 前端页面路径
     */
    private String url;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序号码
     */
    private Integer orderNum;

    /**
     * 状态(true:显示,false:隐藏)
     */
    private Boolean isEnable;

    /**
     * 英文名称
     */
    private String enName;

    /**
     * 可否修改
     */
    private Boolean isModifiable;

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
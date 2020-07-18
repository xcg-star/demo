package com.chenxt.bootdemo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * 拉黑 表
 *
 * @author chenxt
 * @date 2020-07-18
 */
@Data
public class Blacklist implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 编号 - 自增
     */
    @TableId(type = AUTO)
    private Long id;

    /**
     * 拉黑用户编号
     */
    private Long fromUserId;

    /**
     * 被拉黑用户编号
     */
    private Long toUserId;

    /**
     * 是否已删除。0：未删除；1：已删除；
     */
    @TableLogic
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

}
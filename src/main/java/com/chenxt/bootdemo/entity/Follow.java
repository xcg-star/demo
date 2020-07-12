package com.chenxt.bootdemo.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import static com.baomidou.mybatisplus.annotation.IdType.ASSIGN_ID;
import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * 关注 表
 *
 * @author chenxt
 * @date 2020-07-12
 */
@Data
public class Follow implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 编号 - 雪花
     */
    @TableId(type = ASSIGN_ID)
    private Long id;

    /**
     * 关注用户编号
     */
    private Long fromUserId;

    /**
     * 被关注用户编号
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
    private java.util.Date createdAt;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private java.util.Date updatedAt;

}
package com.chenxt.bootdemo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import static com.baomidou.mybatisplus.annotation.IdType.ASSIGN_ID;

/**
 * 用户 表
 *
 * @author chenxt
 * @date 2020-07-07
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 编号 - 雪花
     */
    @TableId(type = ASSIGN_ID)
    private Long id;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 昵称拼音
     */
    private String nickNamePinyin;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 个性签名
     */
    private String bio;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 星座
     */
    private String constellation;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区域
     */
    private String district;

    /**
     * 地址
     */
    private String addr;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 状态(true:正常,false:封号)
     */
    private Boolean isEnable;

    /**
     * 是否已删除。0：未删除；1：已删除；
     */
    @TableLogic
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;

}
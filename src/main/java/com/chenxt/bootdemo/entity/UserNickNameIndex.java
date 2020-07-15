package com.chenxt.bootdemo.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import static com.baomidou.mybatisplus.annotation.IdType.ASSIGN_ID;
import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * 用户昵称索引 表
 *
 * @author chenxt
 * @date 2020-07-15
 */
@Data
public class UserNickNameIndex implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 用户昵称index
     */
    private Integer nickNameIndex;

}
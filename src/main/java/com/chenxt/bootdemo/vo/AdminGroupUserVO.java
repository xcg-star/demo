package com.chenxt.bootdemo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 后台管理用户组的用户VO
 *
 * @author chenxt
 * @date 2020/09/02
 */
@Data
@ApiModel(value = "后台管理用户组的用户VO")
public class AdminGroupUserVO implements Serializable {
    private static final long serialVersionUID = 7281409501289236151L;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "加入时间")
    private Date joinTime;
}

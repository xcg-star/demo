package com.chenxt.bootdemo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 后台管理用户的用户组VO
 *
 * @author chenxt
 * @date 2020/08/26
 */
@Data
@ApiModel(value = "后台管理用户的用户组VO")
public class AdminUserGroupVO implements Serializable {
    private static final long serialVersionUID = 7281409501289236151L;

    @ApiModelProperty(value = "用户组id")
    private Long groupId;

    @ApiModelProperty(value = "用户组名称")
    private String groupName;

    @ApiModelProperty(value = "加入时间")
    private Date joinTime;
}

package com.chenxt.bootdemo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 后台管理用户列表VO
 *
 * @author chenxt
 * @date 2020/09/02
 */
@Data
@ApiModel(value = "后台管理用户列表VO")
public class AdminGroupListVO implements Serializable {
    private static final long serialVersionUID = 3571465512526857369L;

    @ApiModelProperty(value = "用户组id")
    private Long id;

    @ApiModelProperty(value = "用户组名称")
    private String name;

    @ApiModelProperty(value = "成员数")
    private Integer userCount;

    @ApiModelProperty(value = "状态(true:启用,false:禁用)")
    private Boolean isEnable;

    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    @ApiModelProperty(value = "备注")
    private String remark;
}

package com.chenxt.bootdemo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 后台管理用于控制的用户权限VO
 *
 * @author chenxt
 * @date 2020/08/17
 */
@Data
@ApiModel(value = "后台管理用于控制的用户权限VO")
public class AdminUserPermissionForControlVO implements Serializable {
    private static final long serialVersionUID = -4953949965708943325L;

    @ApiModelProperty(value = "父级菜单id")
    private Long parentMenuId;

    @ApiModelProperty(value = "菜单id")
    private Long menuId;

    @ApiModelProperty(value = "菜单(子级菜单名称)")
    private String menuName;

    @ApiModelProperty(value = "前端页面路径")
    private String url;

    @ApiModelProperty(value = "英文名称")
    private String enName;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "操作按钮(页面下所有按钮状态(true:显示,false:隐藏))")
    private Boolean isButtonEnable;
}

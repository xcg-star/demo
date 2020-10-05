package com.chenxt.bootdemo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 后台管理用户的全部权限VO
 *
 * @author chenxt
 * @date 2020/09/02
 */
@Data
@ApiModel(value = "后台管理用户的全部权限VO")
public class AdminUserPermissionAllVO {

    @ApiModelProperty(value = "用户组id")
    private Boolean isExtend;

    @ApiModelProperty(value = "父级菜单id")
    private Long parentMenuId;

    @ApiModelProperty(value = "导航(父级菜单名称)")
    private String parentMenuName;

    @ApiModelProperty(value = "菜单id")
    private Long menuId;

    @ApiModelProperty(value = "菜单(子级菜单名称)")
    private String menuName;

    @ApiModelProperty(value = "权限id")
    private Long permissionId;

    @ApiModelProperty(value = "操作按钮(页面下所有按钮状态(true:显示,false:隐藏))")
    private Boolean isButtonEnable;

    @ApiModelProperty(value = "授权时间")
    private LocalDateTime addTime;
}

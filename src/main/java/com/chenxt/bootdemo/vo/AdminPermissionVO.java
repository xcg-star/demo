package com.chenxt.bootdemo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 后台管理权限VO
 *
 * @author chenxt
 * @date 2020/09/02
 */
@Data
@ApiModel(value = "后台管理权限VO")
public class AdminPermissionVO implements Serializable {
    private static final long serialVersionUID = 2994058077365870331L;

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
}

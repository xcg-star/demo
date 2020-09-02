package com.chenxt.bootdemo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 后台管理权限DTO
 *
 * @author chenxt
 * @date 2020/09/02
 */
@Data
@ApiModel(value = "后台管理权限DTO")
public class AdminPermissionDTO implements Serializable {
    private static final long serialVersionUID = -5880927484666440998L;

    @ApiModelProperty(value = "权限id")
    private Long id;

    @ApiModelProperty(value = "页面下所有按钮状态(true:显示,false:隐藏)")
    private Boolean isButtonEnable;
}

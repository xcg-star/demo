package com.chenxt.bootdemo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 后台管理用户的权限DTO
 *
 * @author chenxt
 * @date 2020/09/02
 */
@Data
@ApiModel(value = "后台管理用户的权限DTO")
public class AdminUserPermissionDTO implements Serializable {
    private static final long serialVersionUID = -9222601091337755532L;

    private Long userId;

    @ApiModelProperty(value = "权限列表")
    private List<AdminPermissionDTO> adminPermissionDTOList;
}

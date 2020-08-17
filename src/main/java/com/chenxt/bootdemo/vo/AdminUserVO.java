package com.chenxt.bootdemo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 后台管理用户VO
 *
 * @author chenxt
 * @date 2020/08/17
 */
@Data
@ApiModel(value = "后台管理用户VO")
public class AdminUserVO implements Serializable {
    private static final long serialVersionUID = -3427769281930188803L;

    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "类型(3:超级管理员,2:管理员,1:普通用户)")
    private Integer type;

    @ApiModelProperty(value = "媒体资源cdn地址")
    private String cdnUrl;

    @ApiModelProperty(value = "媒体资源cdn上传地址")
    private String cdnUploadUrl;

    @ApiModelProperty(value = "权限列表")
    private List<AdminUserPermissionForControlVO> permissionList;
}

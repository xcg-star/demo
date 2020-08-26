package com.chenxt.bootdemo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 后台管理用户VO
 *
 * @author chenxt
 * @date 2020/08/26
 */
@Data
@ApiModel(value = "后台管理用户VO")
public class AdminUserListVO {

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

    @ApiModelProperty(value = "谷歌密钥")
    private String secret;

    @ApiModelProperty(value = "谷歌密钥二维码")
    private String secretQrCode;

    @ApiModelProperty(value = "状态(true:正常,false:封禁)")
    private Boolean isEnable;

    @ApiModelProperty(value = "管理员状态(true:启用,false:禁用)")
    private Boolean isAdminEnable;

    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    @ApiModelProperty(value = "类型(3:超级管理员,2:管理员,1:普通用户)")
    private Integer type;

    @ApiModelProperty(value = "用户组列表")
    private List<AdminUserGroupVO> groupList;
}

package com.chenxt.bootdemo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 后台管理用户更新DTO
 *
 * @author chenxt
 * @date 2020/08/23
 */
@Data
@ApiModel(value = "后台管理用户更新DTO")
public class AdminUserUpdateDTO implements Serializable {
    private static final long serialVersionUID = 3019936545569223508L;

    private Long id;

    @Size(max = 10, message = "name:用户名最大长度为10")
    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @Size(max = 20, min = 6, message = "password:密码长度为6~20")
    @ApiModelProperty(value = "密码")
    private String password;

    @Size(max = 30, message = "remark:备注最大长度为30")
    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "状态(true:启用,false:禁用)")
    private Boolean isEnable;
}

package com.chenxt.bootdemo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 后台管理登陆DTO
 *
 * @author chenxt
 * @date 2020/08/16
 */
@Data
@ApiModel(value = "后台管理登陆DTO")
public class AdminUserLoginDTO implements Serializable {
    private static final long serialVersionUID = -3984549567454812914L;

    @NotBlank(message = "account:账号不能为空")
    @Pattern(regexp = "^[0-9A-Za-z]{6,20}$", message = "account:账号格式为6-20个英文或数字")
    @ApiModelProperty(value = "账号")
    private String account;

    @NotBlank(message = "password:密码不能为空")
    @Size(max = 20, min = 6, message = "password:密码长度为6~20")
    @ApiModelProperty(value = "密码")
    private String password;
}

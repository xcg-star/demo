package com.chenxt.bootdemo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 后台管理验证码DTO
 *
 * @author chenxt
 * @date 2020/08/17
 */
@Data
@ApiModel(value = "后台管理验证码DTO")
public class AdminUserVerifyCodeDTO implements Serializable {
    private static final long serialVersionUID = 6565028997321213139L;

    @NotNull(message = "account:账号不能为空")
    @ApiModelProperty(value = "账号")
    private String account;

    @NotNull(message = "verifyCode:验证码")
    @Pattern(regexp = "^\\d{6}$", message = "verifyCode:验证码必须为6位纯数字")
    @ApiModelProperty(value = "验证码")
    private String verifyCode;
}

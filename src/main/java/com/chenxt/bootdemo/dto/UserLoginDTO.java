package com.chenxt.bootdemo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 用户登陆DTO
 *
 * @author chenxt
 * @date 2020/07/15
 */
@Data
@ApiModel(value = "用户登陆DTO")
public class UserLoginDTO implements Serializable {
    private static final long serialVersionUID = 6283010498268643414L;

    @Pattern(regexp = "^\\+\\d{1,5}\\s\\d{3,11}$", message = "phone:手机号码格式错误")
    @ApiModelProperty(value = "手机号码")
    private String phone;

    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "email:邮箱格式错误")
    @ApiModelProperty(value = "邮箱")
    private String email;

    @Size(min = 6, max = 20, message = "password:密码长度为6~20")
    @ApiModelProperty(value = "密码")
    private String password;

    @Size(min = 6, max = 6, message = "verifyCode:登陆验证码长度为6")
    @ApiModelProperty(value = "登陆验证码")
    private String verifyCode;
}

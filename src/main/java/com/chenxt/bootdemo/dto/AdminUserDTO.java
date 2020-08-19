package com.chenxt.bootdemo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 后台管理用户DTO
 *
 * @author chenxt
 * @date 2020/08/19
 */
@Data
@ApiModel(value = "后台管理用户DTO")
public class AdminUserDTO implements Serializable {
    private static final long serialVersionUID = -6551725055801204547L;

    @NotBlank(message = "name:用户名不能为空")
    @Size(max = 10, message = "name:用户名最大长度为10")
    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @NotBlank(message = "account:账号不能为空")
    //    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{10,20}$", message = "account:账号格式为6-20个英文或数字")
    @Pattern(regexp = "^[0-9A-Za-z]{6,20}$", message = "account:账号格式为6-20个英文或数字")
    @ApiModelProperty(value = "账号")
    private String account;

    @NotBlank(message = "password:密码不能为空")
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$", message = "password:密码格式为6-20个英文或数字")
    @Size(max = 20, min = 6, message = "password:密码长度为6~20")
    @ApiModelProperty(value = "密码")
    private String password;

    @Size(max = 30, message = "remark:备注最大长度为30")
    @ApiModelProperty(value = "备注")
    private String remark;
}

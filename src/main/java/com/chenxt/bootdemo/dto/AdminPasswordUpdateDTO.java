package com.chenxt.bootdemo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 后台管理密码更新DTO
 *
 * @author chenxt
 * @date 2020/08/24
 */
@Data
@ApiModel(value = "后台管理密码更新DTO")
public class AdminPasswordUpdateDTO implements Serializable {
    private static final long serialVersionUID = 4371162425674077208L;

    @NotBlank(message = "oldPassword:旧密码不能为空")
    @Size(max = 20, min = 6, message = "password:密码长度为6~20")
    @ApiModelProperty(value = "旧密码")
    private String oldPassword;

    @NotBlank(message = "newPassword:新密码不能为空")
    @Size(max = 20, min = 6, message = "password:密码长度为6~20")
    @ApiModelProperty(value = "新密码")
    private String newPassword;
}

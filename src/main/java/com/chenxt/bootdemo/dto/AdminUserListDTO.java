package com.chenxt.bootdemo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 后台管理用户列表DTO
 *
 * @author chenxt
 * @date 2020/08/26
 */
@Data
@ApiModel(value = "后台管理用户列表DTO")
public class AdminUserListDTO implements Serializable {
    private static final long serialVersionUID = 867588567253219843L;

    @NotNull(message = "page:页码不能为空")
    @ApiModelProperty(value = "页码")
    private Integer page;

    @NotNull(message = "size:每页条数不能为空")
    @ApiModelProperty(value = "每页条数")
    private Integer size;

    @ApiModelProperty(value = "状态(true:正常,false:封禁)")
    private Boolean isEnable;

    @Size(max = 50, message = "name:用户名最大长度为50")
    @ApiModelProperty(value = "用户名")
    private String name;

    @Size(max = 50, message = "account:账号最大长度为50")
    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "加入的组id")
    private Long groupId;
}

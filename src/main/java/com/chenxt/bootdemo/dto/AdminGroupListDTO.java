package com.chenxt.bootdemo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 后台管理用户组列表DTO
 *
 * @author chenxt
 * @date 2020/09/02
 */
@Data
@ApiModel(value = "后台管理用户组列表DTO")
public class AdminGroupListDTO implements Serializable {
    private static final long serialVersionUID = 2291724347373812429L;

    @NotNull(message = "page:页码不能为空")
    @ApiModelProperty(value = "页码")
    private Integer page;

    @NotNull(message = "size:每页条数不能为空")
    @ApiModelProperty(value = "每页条数")
    private Integer size;

    @Size(max = 50, message = "name:用户组名最大长度为50")
    @ApiModelProperty(value = "用户组名")
    private String name;
}

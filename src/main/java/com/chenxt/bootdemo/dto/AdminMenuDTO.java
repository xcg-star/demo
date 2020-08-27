package com.chenxt.bootdemo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 后台管理菜单DTO
 *
 * @author chenxt
 * @date 2020/08/27
 */
@Data
@ApiModel(value = "后台管理菜单DTO")
public class AdminMenuDTO implements Serializable {
    private static final long serialVersionUID = 8422313868259460820L;

    @ApiModelProperty(value = "父级菜单id")
    private Long parentId;

    @NotBlank(message = "name:名称不能为空")
    @Size(max = 6, message = "name:名称最大长度为6")
    @ApiModelProperty(value = "名称")
    private String name;

    @NotBlank(message = "url:前端页面路径不能为空")
    @ApiModelProperty(value = "前端页面路径")
    private String url;

    @NotBlank(message = "enName:英文名称不能为空")
    @ApiModelProperty(value = "英文名称")
    private String enName;

    @NotBlank(message = "icon:图标不能为空")
    @ApiModelProperty(value = "图标")
    private String icon;

    @Max(value = 100, message = "orderNum:排序号码最大值为100")
    @ApiModelProperty(value = "排序号码")
    private Integer orderNum;
}

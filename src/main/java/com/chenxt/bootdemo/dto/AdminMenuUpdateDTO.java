package com.chenxt.bootdemo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 后台管理菜单更新DTO
 *
 * @author chenxt
 * @date 2020/08/28
 */
@Data
@ApiModel(value = "后台管理菜单更新DTO")
public class AdminMenuUpdateDTO implements Serializable {
    private static final long serialVersionUID = 8422313868259460820L;

    private Long id;

    @Size(max = 6, message = "name:名称最大长度为6")
    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "前端页面路径")
    private String url;

    @ApiModelProperty(value = "英文名称")
    private String enName;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "状态(true:显示,false:隐藏)")
    private Boolean isEnable;

    @ApiModelProperty(value = "排序号码")
    private Integer orderNum;
}

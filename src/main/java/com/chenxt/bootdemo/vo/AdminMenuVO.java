package com.chenxt.bootdemo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 后台管理菜单VO
 *
 * @author chenxt
 * @date 2020/08/30
 */
@Data
@ApiModel(value = "后台管理菜单VO")
public class AdminMenuVO implements Serializable {
    private static final long serialVersionUID = -2888758066755956753L;

    @ApiModelProperty(value = "菜单id")
    private Long id;

    @ApiModelProperty(value = "父级菜单id")
    private Long parentId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "前端页面路径")
    private String url;

    @ApiModelProperty(value = "英文名称")
    private String enName;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "排序号码")
    private Integer orderNum;

    @ApiModelProperty(value = "状态(true:显示,false:隐藏)")
    private Boolean isEnable;
}

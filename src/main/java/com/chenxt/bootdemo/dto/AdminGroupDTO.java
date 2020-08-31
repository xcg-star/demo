package com.chenxt.bootdemo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 后台管理用户组DTO
 *
 * @author chenxt
 * @date 2020/08/31
 */
@Data
@ApiModel(value = "后台管理用户组DTO")
public class AdminGroupDTO implements Serializable {
    private static final long serialVersionUID = 7216444353768322103L;

    @Size(max = 10, message = "name:用户组名最大长度为10")
    @ApiModelProperty(value = "用户组名")
    private String name;

    @Size(max = 30, message = "remark:备注最大长度为30")
    @ApiModelProperty(value = "备注")
    private String remark;
}

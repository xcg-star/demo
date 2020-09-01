package com.chenxt.bootdemo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 后台管理用户组更新DTO
 *
 * @author chenxt
 * @date 2020/09/01
 */
@Data
@ApiModel(value = "后台管理用户组更新DTO")
public class AdminGroupUpdateDTO implements Serializable {

    private static final long serialVersionUID = -6020374049508406883L;

    private Long id;

    @Size(max = 128, message = "name:用户组名最大长度为128")
    @ApiModelProperty(value = "用户组名")
    private String name;

    @Size(max = 512, message = "remark:备注最大长度为512")
    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "状态(true:启用,false:禁用)")
    private Boolean isEnable;
}

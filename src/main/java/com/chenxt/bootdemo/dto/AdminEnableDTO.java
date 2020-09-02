package com.chenxt.bootdemo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 管理员状态DTO
 *
 * @author chenxt
 * @date 2020/09/02
 */
@Data
@ApiModel(value = "管理员状态DTO")
public class AdminEnableDTO implements Serializable {
    private static final long serialVersionUID = 2823092463537828463L;

    @NotNull(message = "isAdminEnable:状态不能为空")
    @ApiModelProperty(value = "管理员状态(true:启用,false:禁用)")
    private Boolean isAdminEnable;
}

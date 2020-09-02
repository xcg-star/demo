package com.chenxt.bootdemo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 后台管理用户组的用户DTO
 *
 * @author chenxt
 * @date 2020/09/02
 */
@Data
@ApiModel(value = "后台管理用户组的用户DTO")
public class AdminGroupUserDTO implements Serializable {
    private static final long serialVersionUID = -7922875859962562231L;

    @ApiModelProperty(value = "用户id列表")
    private List<Long> userIdList;
}

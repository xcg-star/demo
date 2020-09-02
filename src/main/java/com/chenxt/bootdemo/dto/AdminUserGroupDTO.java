package com.chenxt.bootdemo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 后台管理用户的用户组DTO
 *
 * @author chenxt
 * @date 2020/09/02
 */
@Data
@ApiModel(value = "后台管理用户的用户组DTO")
public class AdminUserGroupDTO implements Serializable {
    private static final long serialVersionUID = -7922875859962562231L;

    @ApiModelProperty(value = "用户组id列表")
    private List<Long> groupIdList;
}

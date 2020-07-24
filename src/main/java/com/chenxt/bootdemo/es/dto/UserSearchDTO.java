package com.chenxt.bootdemo.es.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户搜索DTO
 *
 * @author chenxt
 * @date 2020/07/23
 */
@Data
@ApiModel(value = "用户搜索DTO")
public class UserSearchDTO implements Serializable {
    private static final long serialVersionUID = 8045770472002797529L;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "页码")
    private Integer page;

    @ApiModelProperty(value = "每页条数")
    private Integer size;
}

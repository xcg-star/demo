package com.chenxt.bootdemo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 拉黑结果VO
 *
 * @author chenxt
 * @date 2020/07/13
 */
@Data
@ApiModel(value = "拉黑结果VO")
public class BlacklistResultVO implements Serializable {
    private static final long serialVersionUID = -4446229044971906298L;

    @ApiModelProperty(value = "关系(1：本人，2：未关注，3：已关注，4：互相关注，5：已被拉黑，6：已拉黑)")
    private Integer relation;
}

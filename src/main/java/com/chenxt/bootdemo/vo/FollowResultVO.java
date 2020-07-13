package com.chenxt.bootdemo.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 关注结果VO
 *
 * @author chenxt
 * @date 2020/07/13
 */
@Data
@ApiModel(value = "关注结果VO")
public class FollowResultVO implements Serializable {
    private static final long serialVersionUID = -4446229044971906298L;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "关注编号")
    private Long followId;

    @ApiModelProperty(value = "关系(1：本人，2：未关注，3：已关注，4：互相关注，5：已拉黑，6：已被拉黑，7：互相拉黑)")
    private Integer relation;
}

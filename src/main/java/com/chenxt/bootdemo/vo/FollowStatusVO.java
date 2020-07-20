package com.chenxt.bootdemo.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 是否关注VO
 *
 * @author chenxt
 * @date 2020/07/13
 */
@Data
@ApiModel("是否关注VO")
public class FollowStatusVO implements Serializable {

    private static final long serialVersionUID = -6563979825254009778L;

    @ApiModelProperty("关系(1：本人，2：未关注，3：已关注，4：互相关注，5：已被拉黑，6：已拉黑)")
    private Integer relation;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "关注编号(若isFollow为true则有值)")
    private Long followId;
}

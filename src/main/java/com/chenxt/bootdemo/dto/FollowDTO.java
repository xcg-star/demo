package com.chenxt.bootdemo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 关注DTO
 *
 * @author chenxt
 * @date 2020/07/13
 */
@Data
@ApiModel(value = "关注DTO")
public class FollowDTO implements Serializable {
    private static final long serialVersionUID = -6195117645083600087L;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty("被关注用户编号")
    private Long toUserId;
    @ApiModelProperty("被关注话题编号")
    private Long topicId;
}

package com.chenxt.bootdemo.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 关注VO
 *
 * @author chenxt
 * @date 2020/07/13
 */
@Data
@ApiModel(description = "关注VO")
public class FollowVO implements Serializable {
    private static final long serialVersionUID = 8045826202722465621L;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty("编号")
    private Long id;
    @ApiModelProperty("更新时间")
    private Date updatedAt;
    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty("被关注用户编号")
    private Long toUserId;
    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty("粉丝用户编号")
    private Long fromUserId;
    @ApiModelProperty("关系(1：本人，2：未关注，3：已关注，4：互相关注，5：已拉黑，6：已被拉黑，7：互相拉黑)")
    private Integer relation;
}

package com.chenxt.bootdemo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 拉黑DTO
 *
 * @author chenxt
 * @date 2020/07/13
 */
@Data
@ApiModel(value = "拉黑DTO")
public class BlacklistDTO implements Serializable {
    private static final long serialVersionUID = -3393211188329978494L;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty("被拉黑用户编号")
    private Long toUserId;
}

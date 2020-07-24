package com.chenxt.bootdemo.es.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户搜索VO
 *
 * @author chenxt
 * @date 2020/07/23
 */
@Data
@ApiModel(value = "用户搜索VO")
public class UserSearchVO implements Serializable {
    private static final long serialVersionUID = 3997348393070487481L;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "个性签名")
    private String bio;
}

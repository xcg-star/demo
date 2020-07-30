package com.chenxt.bootdemo.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户搜索结果VO
 *
 * @date 2020/07/30
 */
@Data
@ApiModel(value = "用户搜索结果")
public class UserSearchResultVO implements Serializable {

    private static final long serialVersionUID = 6931082103136763197L;
    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "个性签名")
    private String bio;

    @ApiModelProperty(value = "粉丝数")
    private Integer fansCount;

    @ApiModelProperty("关系(1：本人，2：未关注，3：已关注，4：互相关注，5：已拉黑，6：已被拉黑，7：互相拉黑)")
    private Integer relation;
}

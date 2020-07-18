package com.chenxt.bootdemo.base.enumeration;

import com.chenxt.bootdemo.base.expection.BusinessException;
import com.chenxt.bootdemo.base.expection.enumeration.BusinessExceptionCodeEnum;
import lombok.Getter;

/**
 * 关注关系枚举
 *
 * @author chenxt
 * @date 2020/07/18
 */
public enum FollowRelationEnum {
    /**
     * 本人
     */
    SELF(1, "本人"),
    /**
     * 未关注
     */
    NOT_FOLLOW(2, "未关注"),
    /**
     * 已关注
     */
    FOLLOWED(3, "已关注"),
    /**
     * 互相关注
     */
    MUTUAL_FOLLOW(4, "互相关注"),
    /**
     * 已拉黑
     */
    BLACKLISTED(5, "已拉黑"),
    /**
     * 已被拉黑
     */
    IS_BLACKLISTED(6, "已被拉黑"),
    /**
     * 互相拉黑
     */
    MUTUAL_BLACKLIST(7, "互相拉黑"),
    /**
     * 被关注
     */
    IS_FOLLOWED(8, "被关注");

    @Getter
    private Integer code;
    @Getter
    private String value;

    FollowRelationEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static FollowRelationEnum getByCode(Integer code) {
        for (FollowRelationEnum e : FollowRelationEnum.values()) {
            if (e.code.equals(code)) {
                return e;
            }
        }
        throw new BusinessException(BusinessExceptionCodeEnum.INVALID_PARAMETER);
    }

    public static FollowRelationEnum getByValue(String value) {
        for (FollowRelationEnum e : FollowRelationEnum.values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        return null;
    }

    public static Integer getCodeByValue(String value) {
        for (FollowRelationEnum e : FollowRelationEnum.values()) {
            if (e.value.equals(value)) {
                return e.code;
            }
        }
        return null;
    }

    public static String getValueByCode(Integer code) {
        for (FollowRelationEnum e : FollowRelationEnum.values()) {
            if (e.code.equals(code)) {
                return e.value;
            }
        }
        return null;
    }
}

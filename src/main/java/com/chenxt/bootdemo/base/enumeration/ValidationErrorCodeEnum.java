package com.chenxt.bootdemo.base.enumeration;

import com.chenxt.bootdemo.base.expection.assertion.ValidationExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 参数响应枚举
 *
 * @author chenxt
 * @date 2020/07/09
 */
@Getter
@AllArgsConstructor
public enum ValidationErrorCodeEnum implements ValidationExceptionAssert {
    /**
     * 绑定参数校验异常
     */
    VALID_ERROR(-20, "{0}:{1}"),

    ;
    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;
}

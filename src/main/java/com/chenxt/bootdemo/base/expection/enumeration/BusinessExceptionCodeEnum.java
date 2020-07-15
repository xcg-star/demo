package com.chenxt.bootdemo.base.expection.enumeration;

import com.chenxt.bootdemo.base.expection.assertion.BusinessExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应状态码
 *
 * @author chenxt
 * @date 2019/12/25
 */
@Getter
@AllArgsConstructor
public enum BusinessExceptionCodeEnum implements BusinessExceptionAssert {
    /**
     * 调用成功
     */
    SUCCESS(0, "调用成功"),

    /**
     * 调用失败
     */
    FAIL(-1, "调用失败"),

    /**
     * 参数为空或者不合法!
     */
    INVALID_PARAMETER(-3, "参数为空或者不合法!"),

    /**
     * jwt无效！
     */
    JWT_INVALID(-11, "jwt无效！"),

    /**
     * request请求为空！
     */
    REQUEST_IS_EMPTY(-12, "request请求为空！"),

    /**
     * 密码错误
     */
    USER_PASSWORD_ERROR(-202, "密码错误"),

    /**
     * 该手机号码已注册
     */
    PHONE_EXIST_ERROR(-204, "该手机号码已注册"),

    /**
     * 获取用户昵称锁异常
     */
    NICK_NAME_LOCK_GET_ERROR(-233, "获取用户昵称锁异常！"),

    /**
     * 该电子邮箱已注册
     */
    EMAIL_EXIST_ERROR(-242, "该电子邮箱已注册"),

    /**
     * 验证码错误
     */
    VERIFYCODE_ERROR(-205, "验证码错误"),

    /**
     * 验证码已失效
     */
    VERIFYCODE_INVALID(-206, "验证码已失效"),

    /**
     * 不是正确的手机号码
     */
    PHONE_ERROR(-213, "不是正确的手机号码"),

    /**
     * 语言错误
     */
    LANGUAGE_NOT_EXIST(-247, "语言不存在"),

    /**
     * 用户不存在
     */
    USER_NOT_EXISTS(-298, "用户不存在");

    private int code;
    private String message;
}

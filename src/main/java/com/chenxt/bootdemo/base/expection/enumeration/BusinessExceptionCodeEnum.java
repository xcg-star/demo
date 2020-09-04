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
     * 该昵称已被使用
     */
    NICKNAME_EXIST_ERROR(-236, "该昵称已被使用"),

    /**
     * 语言错误
     */
    LANGUAGE_NOT_EXIST(-247, "语言不存在"),

    /**
     * 用户不存在
     */
    USER_NOT_EXISTS(-298, "用户不存在"),

    /**
     * 不允许自己关注自己
     */
    FOLLOW_SELF_NOT_ALLOW(-1103, "不允许自己关注自己"),

    /**
     * 不允许自己拉黑自己
     */
    BLACKLIST_SELF_NOT_ALLOW(-1104, "不允许自己拉黑自己"),

    /**
     * 已被对方拉黑(关注场景)
     */
    IS_BLACKLISTED_FOLLOW(-1107, "由于对方设置，你不能关注TA"),

    /**
     * 已拉黑对方(关注场景)
     */
    BLACKLISTED_FOLLOW(-1109, "您已拉黑用户，请先到个人主页解除拉黑"),

    /**
     * 不允许自己取关自己
     */
    UN_FOLLOW_SELF_NOT_ALLOW(-1112, "不允许自己取关自己"),

    /**
     * 后台管理账号不存在
     */
    ADMIN_USER_ACCOUNT_NOT_EXIST(-1601, "账号不存在!"),

    /**
     * 后台管理密码错误
     */
    ADMIN_USER_PASSWORD_WRONG(-1602, "密码错误!"),

    /**
     * 后台管理验证码错误
     */
    ADMIN_USER_VERIFYCODE_WRONG(-1603, "验证码错误!"),

    /**
     * 后台管理账号已存在
     */
    ADMIN_USER_ACCOUNT_EXIST(-1604, "账号已存在!"),

    /**
     * 后台管理用户名已存在
     */
    ADMIN_USER_NAME_EXIST(-1605, "用户名已存在!"),

    /**
     * 后台管理密码不合法
     */
    ADMIN_USER_PASSWORD_INVALID(-1606, "请输入6-20个英文或数字的密码!"),

    /**
     * 后台管理用户不存在
     */
    ADMIN_USER_NOT_EXIST(-1607, "该用户不存在"),

    /**
     * 后台管理验证码错误
     */
    ADMIN_VERIFYCODE_WRONG(-1608, "验证码错误！"),

    /**
     * 后台管理父级菜单不存在
     */
    ADMIN_PARENT_MENU_NOT_EXIST(-1609, "父级菜单不存在！"),

    /**
     * 后台管理菜单不存在
     */
    ADMIN_MENU_NOT_EXIST(-1610, "菜单不存在！"),

    /**
     * 后台管理菜单重名
     */
    ADMIN_MENU_NAME_EXIST(-1611, "同级菜单名称已存在！"),

    /**
     * 后台管理菜单存在子菜单
     */
    ADMIN_MENU_HAS_CHILDREN(-1612, "删除前请确认该菜单下无子级菜单！"),

    /**
     * 后台管理用户组已存在
     */
    ADMIN_GROUP_EXIST(-1613, "该用户组已存在！"),

    /**
     * 后台管理用户组还存在用户
     */
    ADMIN_GROUP_HAS_USER(-1614, "删除前请确认该组下无用户！"),

    /**
     * 后台管理用户组id不存在
     */
    ADMIN_GROUP_ID_NOT_EXIST(-1615, "id为{0}的用户组不存在！"),

    /**
     * 后台管理用户id不存在
     */
    ADMIN_USER_ID_NOT_EXIST(-1616, "id为{0}的用户不存在！"),

    /**
     * 后台管理权限id不存在
     */
    ADMIN_PERMISSION_ID_NOT_EXIST(-1617, "id为{0}的权限不存在！"),

    /**
     * 后台管理菜单id不存在
     */
    ADMIN_MENU_ID_NOT_EXIST(-1618, "id为{0}的菜单不存在！"),

    /**
     * 后台管理用户类型不合法
     */
    ADMIN_USER_TYPE_INVALID(-1619, "用户类型不合法！"),

    /**
     * 后台管理无权限操作
     */
    ADMIN_PERMISSION_DENIED(-1620, "无权限操作！"),

    ;

    private int code;
    private String message;
}

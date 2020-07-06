package com.chenxt.bootdemo.base.enumeration;

import java.util.Arrays;

/**
 * 响应状态码
 *
 * @author chenxt
 * @date 2019/12/25
 */
public enum CodeStatusEnum {
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
     * 系统繁忙，请稍后再试
     */
    OPERATE_FAIL(-4, "系统繁忙，请稍后再试!"),

    /**
     * 登录已过期
     */
    LAND_EXPIRATION(-5, "登录已过期，为保障账号安全，系统要求您重新登录!"),

    /**
     * 身份验证失败
     */
    UNAUTHORIZED(-6, "身份验证失败!"),

    /**
     * 签名错误
     */
    SIGNATURE_ERROR(-7, "签名错误!"),

    /**
     * 没有权限
     */
    HAVE_NOT_PERMISSION(-8, "对不起！您没有这权限！"),

    /**
     * 版本号有误
     */
    VERSION_ERROR(-9, "版本号有误！"),

    /**
     * 已存在的参数
     */
    EXISTING_PARAMETER(-10, "参数已存在"),

    /**
     * jwt无效！
     */
    JWT_INVALID(-11, "jwt无效！"),

    /**
     * 缺少ver!
     */
    MISSING_VERSION(-100, "缺少ver!"),

    /**
     * 缺少os!
     */
    MISSING_OS(-101, "缺少os!"),

    /**
     * 缺少uid
     */
    MISSING_UID(-102, "缺少uid!"),

    /**
     * 非法的uid
     */
    INVALID_UID(-103, "非法的uid!"),

    /**
     * 缺少token
     */
    MISSING_TOKEN(-104, "缺少token!"),

    /**
     * 缺少channel
     */
    MISSING_CHANNEL(-105, "缺少channel!"),

    /**
     * 当前为游客帐号，请登录
     */
    GUEST_NEED_LOGIN(-200, "当前为游客帐号，请登录!"),

    /**
     * 用户名错误
     */
    USER_NAME_ERROR(-201, "用户名错误"),

    /**
     * 密码错误
     */
    USER_PASSWORD_ERROR(-202, "密码错误"),

    /**
     * 钉钉用户错误
     */
    DINTALK_USER_ERROR(-203, "钉钉用户错误"),

    /**
     * 该手机号码已注册
     */
    PHONE_EXIST_ERROR(-204, "该手机号码已注册"),

    /**
     * 验证码错误
     */
    VERIFYCODE_ERROR(-205, "验证码错误"),

    /**
     * 验证码已失效
     */
    VERIFYCODE_INVAID(-206, "验证码已失效"),

    /**
     * 该用户不存在
     */
    USER_NOT_EXIST(-207, "该用户不存在"),

    /**
     * 该用户已存在
     */
    USER_EXIST(-208, "该用户已存在"),

    /**
     * 微信用户错误
     */
    WECHAT_USER_ERROR(-209, "微信用户错误"),

    /**
     * 该微信账号未绑定手机号，请先绑定手机号
     */
    WECHAT_NOT_BIND(-210, "该微信账号未绑定手机号，请先绑定手机号"),

    /**
     * QQ用户错误
     */
    QQ_USER_ERROR(-211, "QQ用户错误"),

    /**
     * 该QQ账号未绑定手机号，请先绑定手机号
     */
    QQ_NOT_BIND(-212, "QQ账号未绑定手机号，请先绑定手机号"),

    /**
     * 不是正确的手机号码
     */
    PHONE_ERROR(-213, "不是正确的手机号码"),

    /**
     * 用户已被永久封禁
     */
    USER_IS_BANNED_FOREVER(-214, "该帐号因违规操作行为，被执行永久封号处罚，有疑问可致电人工客服了解详情"),

    /**
     * 用户已被暂时封禁
     */
    USER_IS_BANNED(-215, "该帐号因违规操作行为，已被封停账号。解封时间 %s。\n" +
            "请规范用户行为，并尊重平台用户协议。"),

    /**
     * 手机号不存在
     */
    PHONE_NOT_EXIST(-216, "手机号不存在"),

    /**
     * 解密发生错误
     */
    DECRYPT_ERROR(-217, "解密发生错误"),

    /**
     * 解密信息不正确
     */
    DECRYPT_INFO_INCORRECT(-218, "解密信息不正确"),

    /**
     * 绑定失败，该手机号已绑定在另一个账号上
     */
    THIRD_PARTY_BINDED(-219, "绑定失败，该手机号已绑定在另一个账号上"),

    /**
     * APPLE用户错误
     */
    APPLE_USER_ERROR(-220, "APPLE用户错误"),

    /**
     * 该apple账号未绑定手机号，请先绑定手机号
     */
    APPLE_NOT_BIND(-221, "该apple账号未绑定手机号，请先绑定手机号"),

    /**
     * 极光id不存在
     */
    JPUSH_NOT_EXIST(-222, "极光id不存在"),

    /**
     * 微信小程序用户错误
     */
    WECHAT_AMP_USER_ERROR(-223, "微信小程序用户错误"),

    /**
     * 该微信小程序账号未绑定手机号，请先绑定手机号
     */
    WECHAT_AMP_NOT_BIND(-224, "该微信小程序账号未绑定手机号，请先绑定手机号"),

    /**
     * 发送验证码失败，请重试
     */
    VERIFYCODE_SEND_ERROR(-225, "发送验证码失败，请重试"),

    /**
     * 发送验证码超频
     */
    VERIFYCODE_SEND_LIMIT(-226, "你今天已获取%s次验证码，不可再获取"),

    /**
     * 发送验证码短时超频
     */
    VERIFYCODE_SEND_LIMIT_SHORT(-227, "获取验证码太频繁"),

    /**
     * 二维码无效
     */
    QRCODE_INVALID(-228, "二维码无效"),

    /**
     * 二维码已过期
     */
    QRCODE_EXPIRED(-229, "二维码已过期"),

    /**
     * 还未扫码
     */
    QRCODE_NOT_SCANNED(-230, "还未扫码"),

    /**
     * 当前用户已被注销！
     */
    USER_CANCELLED(-231, "当前用户已被注销！"),

    /**
     * 敏感词
     */
    SENSITIVE(-232, "%s包含敏感词，要不换个词语吧！"),

    /**
     * 获取用户昵称锁异常
     */
    NICK_NAME_LOCK_GET_ERROR(-233, "获取用户昵称锁异常！"),

    /**
     * 对方用户已被永久封禁
     */
    TO_USER_IS_BANNED_FOREVER(-234, "该帐号因违规操作行为，被执行永久封号处罚"),

    /**
     * 对方用户已被暂时封禁
     */
    TO_USER_IS_BANNED(-235, "该帐号因违规操作行为，已被封停账号。解封时间 %s。"),

    /**
     * 该昵称已被使用
     */
    NICKNAME_EXIST_ERROR(-236, "该昵称已被使用"),

    /**
     * 数据库中无此用户
     */
    USER_NOT_EXIST_IN_DB(-237, "数据库中无此用户"),

    /**
     * 设备id为空
     */
    DEVICE_ID_EMPTY(-238, "设备id不能为空!"),

    /**
     * 电子邮箱不存在
     */
    EMAIL_NOT_EXIST(-239, "电子邮箱不存在"),

    /**
     * 该用户未绑定电子邮箱
     */
    EMAIL_NOT_BIND(-240, "该用户未绑定电子邮箱"),

    /**
     * 该用户未绑定手机号码
     */
    PHONE_NOT_BIND(-241, "该用户未绑定手机号码"),

    /**
     * 该电子邮箱已注册
     */
    EMAIL_EXIST_ERROR(-242, "该电子邮箱已注册"),

    /**
     * 未设置密码
     */
    PASSWORD_NOT_SET(-243, "未设置密码"),

    /**
     * 获取支付验证码失败
     */
    GET_PAY_VERIFY_CDDE_ERROR(-244, "获取支付验证码失败"),

    /**
     * 生成邀请码失败
     */
    GENERATE_INVITE_CODE_ERROR(-245, "生成邀请码失败"),

    /**
     * 绑定邀请码失败
     */
    BIND_INVITE_CODE_ERROR(-246, "绑定邀请码失败"),

    /**
     * 语言错误
     */
    LANGUAGE_NOT_EXIST(-247, "语言不存在"),

    /**
     * 无可用短信渠道
     */
    AVAILABLE_SMS_CHANNEL_NOT_EXIST(-248, "无可用短信渠道，请联系客服!"),

    /**
     * 用户不存在
     */
    USER_NOT_EXISTS(-298, "说实话,auth表有这条数据，user却没有，大概率是首次登陆的时候往user表插数据时报错了"),

    /**
     * 用户id不存在
     */
    CURRENT_USER_ID_NOT_EXISTS(-299, "说实话,这个jwt-token的用户id不存在,被物理删除了吧(请注意是不是连错了环境，别被我逮到)"),

    /**
     * 该话题名已存在
     */
    TOPIC_NAME_EXIST_ERROR(-301, "该话题名已存在"),

    /**
     * 该话题名已存在
     */
    TOPIC_ID_EXIST_ERROR(-302, "该话题编号不存在或者已被删除"),

    /**
     * 帖子包含敏感词
     */
    INCLUDE_FORBIDDEN_SENSITIVE_WORD(-886, "您输入的内容包含敏感词，拒绝使用，请重新输入"),

    /**
     * 帖子内容过长
     */
    POST_CONTENT_OVER_LONG(-887, "帖子文本内容过长"),

    /**
     * 无法找到对应的帖子
     */
    POST_NOT_FOUND(-1001, "无法找到对应的帖子!"),

    /**
     * 评论不存在
     */
    COMMENT_NOT_FOUND(-1002, "评论不存在!"),

    /**
     * 话题不存在
     */
    TOPIC_NOT_FOUND(-1003, "话题不存在!"),

    /**
     * 回复不存在
     */
    REPLY_NOT_FOUND(-1004, "回复不存在!"),

    /**
     * 回复不存在
     */
    MOVIE_NOT_FOUND(-1005, "影视不存在!"),

    /**
     * 关注信息不存在
     */
    FOLLOW_NOT_FOUND(-1101, "关注信息不存在!"),

    /**
     * 重复提交
     */
    DUPLICATE_COMMIT(-1102, "重复提交"),

    /**
     * 不允许自己关注自己
     */
    FOLLOW_SELF_NOT_ALLOW(-1103, "不允许自己关注自己"),

    /**
     * 不允许自己拉黑自己
     */
    BLACKLIST_SELF_NOT_ALLOW(-1104, "不允许自己拉黑自己"),

    /**
     * 已被对方拉黑
     */
    IS_BLACKLISTED(-1105, "已被对方拉黑"),

    /**
     * 已把对方拉黑
     */
    BLACKLISTED(-1106, "已把对方拉黑"),

    /**
     * 已被对方拉黑(关注场景)
     */
    IS_BLACKLISTED_FOLLOW(-1107, "由于对方设置，你不能关注TA"),

    /**
     * 已被对方拉黑(评论场景)
     */
    IS_BLACKLISTED_COMMENT(-1108, "由于对方设置，你不能评论TA"),

    /**
     * 已拉黑对方(关注场景)
     */
    BLACKLISTED_FOLLOW(-1109, "您已拉黑用户，请先到个人主页解除拉黑"),

    /**
     * 已被对方拉黑(回复场景)
     */
    IS_BLACKLISTED_REPLAY(-1110, "由于对方设置，你不能回复TA"),

    /**
     * 已被对方拉黑(@场景)
     */
    IS_BLACKLISTED_AT(-1111, "由于对方设置，你不能@TA"),

    /**
     * 不允许自己取关自己
     */
    UN_FOLLOW_SELF_NOT_ALLOW(-1112, "不允许自己取关自己"),

    /**
     * 帖子正在审核中
     */
    POST_IN_EXAMINE(-1201, "帖子正在审核中"),

    /**
     * 帖子正在审核中
     */
    POST_IN_VIOLATION(-1202, "帖子已违规"),

    /**
     * 帖子被禁用
     */
    POST_DISABLED(-1203, "很抱歉，该帖子被设置不对外开放"),

    /**
     * 需要帖子创建人
     */
    POST_CREATOR_ID_NEED(-1204, "需要postCreatorId"),

    /**
     * 需要评论创建人
     */
    COMMENT_CREATOR_ID_NEED(-1205, "需要commentCreatorId"),

    /**
     * 密码长度错误
     */
    PASSWORD_LENGTH_INCORRECT(-208, "密码长度错误，请输入6～26位长度密码"),

    /**
     * 数据为空
     */
    DATA_EMPTY(-1301, "数据为空!"),

    /**
     * 搜索发生错误
     */
    SEARCH_ERROR(-1401, "搜索发生错误!"),

    /**
     * 推送错误
     */
    PUSH_ERROR(-1501, "推送发生错误!"),

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
    ADMIN_GROUP_ID_NOT_EXIST(-1615, "id为%s的用户组不存在！"),

    /**
     * 后台管理用户id不存在
     */
    ADMIN_USER_ID_NOT_EXIST(-1616, "id为%s的用户不存在！"),

    /**
     * 后台管理权限id不存在
     */
    ADMIN_PERMISSION_ID_NOT_EXIST(-1617, "id为%s的权限不存在！"),

    /**
     * 后台管理菜单id不存在
     */
    ADMIN_MENU_ID_NOT_EXIST(-1618, "id为%s的菜单不存在！"),

    /**
     * 后台管理用户类型不合法
     */
    ADMIN_USER_TYPE_INVALID(-1619, "用户类型不合法！"),

    /**
     * 后台管理无权限操作
     */
    ADMIN_PERMISSION_DENIED(-1620, "无权限操作！"),

    /**
     * 用户认证信息已存在
     */
    USER_AUTH_AUDIT_EXISTING(-1701, "用户认证信息已存在！"),

    /**
     * 用户审核信息不存在
     */
    USER_AUTH_AUDIT_NOT_EXIST(-1702, "用户审核信息不存在！"),

    /**
     * 用户认证已审核
     */
    USER_AUTH_AUDITED(-1703, "用户认证已审核！"),

    /**
     * 用户UP主认证标签不存在
     */
    USER_UP_TAG_NOT_EXIST(-1704, "用户认证标签不存在"),

    /**
     * 推送失败
     */
    PUSH_FAIL(201, "推送失败"),

    /**
     * 分享失败
     */
    SHARE_FAIL(-1801, "分享失败");

    private Integer code;
    private String message;

    CodeStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }


    /**
     * 根据code获取value
     *
     * @param code code
     * @return value by code
     */
    public static CodeStatusEnum getByCode(Integer code) {
        return Arrays.stream(CodeStatusEnum.values())
                .filter(codeStatusEnum -> codeStatusEnum.getCode().equals(code)).findFirst().get();
    }

    /**
     * Gets by message.
     *
     * @param value the value
     * @return the by message
     */
    public static CodeStatusEnum getByMessage(String value) {
        return Arrays.stream(CodeStatusEnum.values())
                .filter(codeStatusEnum -> codeStatusEnum.getMessage().equals(value)).findFirst().get();
    }
}

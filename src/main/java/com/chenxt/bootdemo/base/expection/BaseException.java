package com.chenxt.bootdemo.base.expection;


import com.chenxt.bootdemo.base.expection.enumeration.BusinessExceptionCodeEnum;
import com.chenxt.bootdemo.base.expection.core.IResponseEnum;
import lombok.Getter;

/**
 * The type Base exception.
 *
 * @author chenxt
 * @date 2020/03/25
 */
@Getter
public class BaseException extends RuntimeException {
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 返回码
     */
    protected IResponseEnum responseEnum;

    /**
     * 异常消息参数
     */
    protected Object[] args;

    /**
     * Instantiates a new Base exception.
     *
     * @param code    the code
     * @param message the message
     */
    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.responseEnum = new IResponseEnum() {
            @Override
            public int getCode() {
                return code;
            }

            @Override
            public String getMessage() {
                return message;
            }
        };
    }

    /**
     * Instantiates a new Base exception.
     *
     * @param status the status
     */
    public BaseException(BusinessExceptionCodeEnum status) {
        super(status.getMessage());
        this.code = code;
    }

    /**
     * Instantiates a new Base exception.
     *
     * @param responseEnum the response enum
     */
    public BaseException(IResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.responseEnum = responseEnum;
    }

    /**
     * Instantiates a new Base exception.
     *
     * @param responseEnum the response enum
     * @param args         the args
     * @param message      the message
     */
    public BaseException(IResponseEnum responseEnum, Object[] args, String message) {
        super(message);
        this.responseEnum = responseEnum;
        this.args = args;
    }

    /**
     * Instantiates a new Base exception.
     *
     * @param responseEnum the response enum
     * @param args         the args
     * @param message      the message
     * @param cause        the cause
     */
    public BaseException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
        super(message, cause);
        this.responseEnum = responseEnum;
        this.args = args;
    }
}

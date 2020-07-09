package com.chenxt.bootdemo.base.expection;

import com.chenxt.bootdemo.base.expection.core.IResponseEnum;

/**
 * <p> 校验异常 </p>
 * <p> 调用接口时，参数格式不合法可以抛出该异常 </p>
 *
 * @author chenxt
 * @date 2020/07/09
 */
public class ValidationException extends BaseException {
    /**
     * Instantiates a new Validation exception.
     *
     * @param code    the code
     * @param message the message
     */
    public ValidationException(Integer code, String message) {
        super(code, message);
    }

    /**
     * Instantiates a new Validation exception.
     *
     * @param responseEnum the response enum
     * @param args         the args
     * @param message      the message
     */
    public ValidationException(IResponseEnum responseEnum, Object[] args, String message) {
        super(responseEnum, args, message);
    }

    /**
     * Instantiates a new Validation exception.
     *
     * @param responseEnum the response enum
     * @param args         the args
     * @param message      the message
     * @param cause        the cause
     */
    public ValidationException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
        super(responseEnum, args, message, cause);
    }
}

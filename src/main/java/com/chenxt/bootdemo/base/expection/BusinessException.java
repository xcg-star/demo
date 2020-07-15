package com.chenxt.bootdemo.base.expection;

import com.chenxt.bootdemo.base.expection.core.IResponseEnum;

/**
 * <p> 业务异常 </p>
 * <p> 业务处理时，出现异常，可以抛出该异常 </p>
 *
 * @author chenxt
 * @date 2020/04/06
 */
public class BusinessException extends BaseException {
    /**
     * Instantiates a new Business exception.
     *
     * @param responseEnum the response enum
     */
    public BusinessException(IResponseEnum responseEnum) {
        super(responseEnum);
    }

    /**
     * Instantiates a new Business exception.
     *
     * @param responseEnum the response enum
     * @param args         the args
     * @param message      the message
     */
    public BusinessException(IResponseEnum responseEnum, Object[] args, String message) {
        super(responseEnum, args, message);
    }

    /**
     * Instantiates a new Business exception.
     *
     * @param responseEnum the response enum
     * @param args         the args
     * @param message      the message
     * @param cause        the cause
     */
    public BusinessException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
        super(responseEnum, args, message, cause);
    }
}

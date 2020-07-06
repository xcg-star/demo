package com.chenxt.bootdemo.base.expection;

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
     * @param code    the code
     * @param message the message
     */
    public BusinessException(Integer code, String message) {
        super(code, message);
    }
}

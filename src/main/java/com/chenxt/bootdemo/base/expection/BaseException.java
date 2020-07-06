package com.chenxt.bootdemo.base.expection;


import com.chenxt.bootdemo.base.enumeration.CodeStatusEnum;

/**
 * The type Base exception.
 *
 * @author chenxt
 * @date 2020/03/25
 */
public class BaseException extends RuntimeException {
    /**
     * 错误码
     */
    private Integer code;

    /**
     * Instantiates a new Base exception.
     *
     * @param code    the code
     * @param message the message
     */
    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * Instantiates a new Base exception.
     *
     * @param message the message
     * @param code    the code
     */
    public BaseException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    /**
     * Instantiates a new Base exception.
     *
     * @param status the status
     */
    public BaseException(CodeStatusEnum status) {
        super(status.getMessage());
        this.code = code;
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
}

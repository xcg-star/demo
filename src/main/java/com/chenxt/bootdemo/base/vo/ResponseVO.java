package com.chenxt.bootdemo.base.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 回复DTO§
 *
 * @param <T> the type parameter
 * @author chenxt
 * @date 2020/07/09
 */
@Data
@Builder
@AllArgsConstructor
public class ResponseVO<T> implements Serializable {

    private static final long serialVersionUID = -2596119551473484211L;

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 时间戳
     */
    private Long timestamp;

    /**
     * 结果数据
     */
    private T data;

    /**
     * Instantiates a new Response vo.
     *
     * @param code    the code
     * @param message the message
     * @param data    the data
     */
    public ResponseVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

}

package com.chenxt.bootdemo.base.advice;

import lombok.Data;

/**
 * 全局返回包装
 *
 * @author chenxt
 * @date 2020/07/15
 */
@Data
public class ComposeVO {
    Integer code = 0;
    String message;
    Object data;

    public ComposeVO(Object o) {
        this.data = o;
    }
}

package com.chenxt.bootdemo.base.security;

import java.lang.annotation.*;

/**
 * 装配Token注解
 *
 * @author chenxt
 * @date 2020/07/14
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TokenParam {

}

package com.chenxt.bootdemo.base.security;

import java.lang.annotation.*;

/**
 * 忽略Token
 *
 * @author chenxt
 * @date 2020/07/14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreToken {

}

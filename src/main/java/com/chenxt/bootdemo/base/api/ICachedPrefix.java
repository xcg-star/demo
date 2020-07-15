package com.chenxt.bootdemo.base.api;

/**
 * 缓存前缀定义
 *
 * @author chenxt
 * @date 2020/07/15
 */
public interface ICachedPrefix {

    /**
     * 用户昵称索引分布式锁key
     */
    String bootdemo_global_user_nickname_index = "bootdemo:global:user:nickname:index";

    /**
     * 用户昵称索引
     */
    String bootdemo_user_nickname_index = "bootdemo:user:nickname:index";

    /**
     * 验证码
     */
    String bootdemo_user_verifycode = "bootdemo:user:verifycode:%s:%s";

    /**
     * 验证码票根
     */
    String bootdemo_user_verifycode_ticket = "bootdemo:user:verifycode:ticket:%s:%s";
}

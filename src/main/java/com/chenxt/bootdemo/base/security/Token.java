package com.chenxt.bootdemo.base.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 当前登陆用户信息
 *
 * @author chenxt
 * @date 2020/07/14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    private Long currentUserId;
}

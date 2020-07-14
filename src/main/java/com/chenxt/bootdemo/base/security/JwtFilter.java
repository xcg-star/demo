package com.chenxt.bootdemo.base.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * jwt过滤器
 *
 * @author chenxt
 * @date 2020/07/14
 */
@Slf4j
@Component
public class JwtFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 判断header是否有token
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String jwtToken = httpServletRequest.getHeader("jwt-token");
        try {
            if (!StringUtils.isEmpty(jwtToken)) {
                Token token = JwtUtil.getSubjectByJwt(jwtToken);
                // 刷新jwt-token
                httpServletResponse.setHeader("jwt-token", JwtUtil.createJwt(token));
            }
        } catch (Exception e) {
            log.warn("jwt-token格式不合法，跳过", e);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}

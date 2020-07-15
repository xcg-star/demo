package com.chenxt.bootdemo.base.advice;

import com.chenxt.bootdemo.base.expection.enumeration.BusinessExceptionCodeEnum;
import com.chenxt.bootdemo.base.util.ResponseVOUtils;
import com.chenxt.bootdemo.base.vo.ResponseVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.LinkedHashMap;

/**
 * 全局返回处理
 *
 * @author chenxt
 * @date 2002/07/15
 */
@Configuration
@RestControllerAdvice
public class ComposeReturnValueAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        String path = serverHttpRequest.getURI().getPath();
        if (StringUtils.contains(path, "swagger")) {
            return o;
        }
        if (StringUtils.contains(path, "api-docs")) {
            return o;
        }
        ServletServerHttpResponse response = (ServletServerHttpResponse) serverHttpResponse;
        int status = response.getServletResponse().getStatus();
        if (HttpStatus.OK.value() != status) {
            response.setStatusCode(HttpStatus.OK);
            if (o.getClass().equals(LinkedHashMap.class) && ((LinkedHashMap) o).get("message").equals(BusinessExceptionCodeEnum.JWT_INVALID.getMessage())) {
                return ResponseVOUtils.error(BusinessExceptionCodeEnum.JWT_INVALID.getCode(), BusinessExceptionCodeEnum.JWT_INVALID.getMessage());
            }
            return o;
        }
        if (o != null && o.getClass().equals(ResponseVO.class)) {
            return o;
        }
        return new ComposeVO(o);
    }
}

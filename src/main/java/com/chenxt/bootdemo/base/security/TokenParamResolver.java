package com.chenxt.bootdemo.base.security;

import com.chenxt.bootdemo.base.enumeration.BusinessExceptionCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 装配Token
 *
 * @author chenxt
 * @date 2020/07/14
 */
@Slf4j
public class TokenParamResolver implements HandlerMethodArgumentResolver {

    /**
     * 入参筛选
     *
     * @param methodParameter 参数集合
     * @return 格式化后的参数
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(TokenParam.class) && methodParameter.getParameterType().equals(Token.class);
    }

    /**
     * 装配Token
     *
     * @param methodParameter       入参集合
     * @param modelAndViewContainer model 和 view
     * @param nativeWebRequest      web相关
     * @param webDataBinderFactory  入参解析
     * @return 包装对象
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        BusinessExceptionCodeEnum.REQUEST_IS_EMPTY.assertNotNull(request);
        String jwtToken = request.getHeader("jwt-token");
        if (!StringUtils.isEmpty(jwtToken)) {
            return JwtUtil.getSubjectByJwt(jwtToken);
        } else {
            return Token.builder().build();
        }
    }
}

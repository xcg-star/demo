package com.chenxt.bootdemo.base.config;

import com.chenxt.bootdemo.base.security.TokenParamResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 使用RestControllerAdvice的beforeBodyWrite方法时，在处理String时，实际处理的HttpMessageConverter，应该是MappingJackson2HttpMessageConverter
 *
 * @author chenxt
 * @date 2020/07/14
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    /**
     * 配置json序列化
     *
     * @param converters 转换器
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 0-1可配，2-8是默认加载的
        converters.add(0, new MappingJackson2HttpMessageConverter());
    }

    /**
     * Token参数解析
     *
     * @param argumentResolvers 解析类
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        //注入用户信息
        argumentResolvers.add(new TokenParamResolver());
    }

    /**
     * 静态资源处理
     *
     * @param registry 注册器
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}

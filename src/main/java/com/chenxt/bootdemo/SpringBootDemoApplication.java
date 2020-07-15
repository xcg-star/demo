package com.chenxt.bootdemo;

import com.chenxt.bootdemo.base.expection.handler.UnifiedApiExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 启动类
 *
 * @author chenxt
 * @date 2020/07/15
 */
@SpringBootApplication
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

    @Bean(name = "apiExceptionHandler")
    public UnifiedApiExceptionHandler unifiedApiExceptionHandler() {
        return new UnifiedApiExceptionHandler();
    }
}

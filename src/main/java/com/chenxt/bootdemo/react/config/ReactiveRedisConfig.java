package com.chenxt.bootdemo.react.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * redis react 配置
 *
 * @author chenxt
 * @date ：2020/07/25
 */
public class ReactiveRedisConfig {

    @Resource
    RedisStandaloneConfiguration redisStandaloneConfiguration;

    @Bean
    public ReactiveRedisConnectionFactory lettuceConnectionFactory() {

        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .useSsl().and()
                .commandTimeout(Duration.ofSeconds(2))
                .shutdownTimeout(Duration.ZERO)
                .build();

        return new LettuceConnectionFactory(redisStandaloneConfiguration, clientConfig);
    }
}

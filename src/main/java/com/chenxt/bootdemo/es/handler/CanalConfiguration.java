package com.chenxt.bootdemo.es.handler;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * canal configuration
 *
 * @author chenxt
 * @date 2020/07/23
 */
@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "canal")
public class CanalConfiguration {

    private Map<String, String> major;

}

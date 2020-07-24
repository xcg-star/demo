package com.chenxt.bootdemo.es.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * canal auto config
 *
 * @author chenxt
 * @date 2020/07/23
 */
@Configuration
public class EsDataWriterAutoConfig {

    @Resource
    private CanalConfiguration canalConfiguration;

    @Resource
    CdcAction cdcAction;

    @Bean
    public CanalMajorService canalService() {
        Map<String, String> major = canalConfiguration.getMajor();
        return new CanalMajorService(major.get("destination"),
                major.get("username"),
                major.get("password"),
                major.get("ip"),
                Integer.parseInt(major.get("port")),
                major.get("zkServers"),
                cdcAction);
    }
}

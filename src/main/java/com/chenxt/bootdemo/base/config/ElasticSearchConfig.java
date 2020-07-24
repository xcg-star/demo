package com.chenxt.bootdemo.base.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.elasticsearch.rest.RestClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;

/**
 * ES配置类
 *
 * @author chenxt
 * @date 2020/07/23
 */
@Configuration
@Slf4j
public class ElasticSearchConfig {
    @Resource
    private RestClientProperties restClientProperties;

    private static final int CONNECT_TIME_OUT = 5000;
    private static final int SOCKET_TIME_OUT = 30000;
    private static final int CONNECTION_REQUEST_TIME_OUT = 500;

    private static final int MAX_CONNECT_NUM = 10;
    private static final int MAX_CONNECT_PER_ROUTE = 30;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        List<String> uris = restClientProperties.getUris();
        String username = restClientProperties.getUsername();
        String password = restClientProperties.getPassword();
        String addresses = uris.get(0);
        String[] addressArr = addresses.split(",");
        HttpHost[] httpHosts = new HttpHost[addressArr.length];
        //将地址转换为http主机数组
        for (int i = 0; i < httpHosts.length; i++) {
            String[] ipAndPort = addressArr[i].split(":");
            httpHosts[i] = new HttpHost(ipAndPort[0], Integer.parseInt(ipAndPort[1]), "http");
        }

        log.info("注册 ES 信息：{}", uris.toString());
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(httpHosts)
                //设置请求配置回调
                .setRequestConfigCallback(requestConfigBuilder -> {
                    //设置连接接超时时间
                    requestConfigBuilder.setConnectTimeout(CONNECT_TIME_OUT);
                    //设置Socket超时时间
                    requestConfigBuilder.setSocketTimeout(SOCKET_TIME_OUT);
                    //设置连接请求超时时间
                    requestConfigBuilder.setConnectionRequestTimeout(CONNECTION_REQUEST_TIME_OUT);
                    return requestConfigBuilder;
                })
                .setHttpClientConfigCallback((httpClientBuilder) -> {
                    //设置最大连接数
                    httpClientBuilder.setMaxConnTotal(MAX_CONNECT_NUM);
                    //设置每个路线的最大连接数
                    httpClientBuilder.setMaxConnPerRoute(MAX_CONNECT_PER_ROUTE);
                    //判断配置了用户名，则进行用户名密码连接
                    if (StringUtils.isNotEmpty(username)) {
                        // 禁用身份缓存
                        httpClientBuilder.disableAuthCaching();
                        // 设置账号密码
                        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
                        httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                    return httpClientBuilder;
                })
        );
        return client;
    }
}

package com.chenxt.bootdemo.base.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * liquibase配置
 *
 * @author chenxt
 * @date 2020/02/29
 */
@Configuration
public class LiquibaseConfig {

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        // 数据源直接在application.yml中配置
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setChangeLog("classpath:liquibase/sql/major_db.sql");
        springLiquibase.setContexts("develop,release");
        springLiquibase.setShouldRun(true);
        return springLiquibase;
    }

}
package com.gs.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    // 数据源1
    @Bean(name = "primaryDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    // 数据源2
    // @Bean(name = "secondaryDataSource")
    // @ConfigurationProperties(prefix = "spring.datasource.ds2")
    // public DataSource secondaryDataSource() {
    //     return DataSourceBuilder.create().build();
    // }
}

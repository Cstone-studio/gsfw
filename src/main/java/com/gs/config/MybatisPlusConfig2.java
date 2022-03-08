package com.gs.config;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * Mybatis数据源2配置
 */
@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
@MapperScan(basePackages = MybatisPlusConfig2.PACKAGE, sqlSessionFactoryRef = "sqlSessionFactory2")
public class MybatisPlusConfig2 {
    static final String PACKAGE = "com.gs.repository.mybatis.db2";
    static final String MAPPER_LOCATION = "classpath:mapper/db2/*.xml";

    @Bean("db2")
    @ConfigurationProperties(prefix = "spring.datasource.druid.db2")
    public DataSource druidDataSource1() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

    @Bean("sqlSessionFactory2")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("db2")DataSource db2) throws Exception {
        MybatisSqlSessionFactoryBean  sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(db2);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MybatisPlusConfig2.MAPPER_LOCATION));
        return sqlSessionFactoryBean.getObject();
    }
}

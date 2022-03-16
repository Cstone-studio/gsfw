package com.gs.config;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * Mybatis数据源1配置
 */
@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
@ConditionalOnClass(value = {PaginationInterceptor.class})
@MapperScan(basePackages = MybatisPlusConfig.PACKAGE, sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisPlusConfig implements TransactionManagementConfigurer {
    static final String PACKAGE = "com.gs.repository.mybatis";
    static final String MAPPER_LOCATION = "classpath:mapper/db1/*.xml";

    @Bean("db1")
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource druidDataSource1() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("db1")DataSource db1) throws Exception {
        MybatisSqlSessionFactoryBean  sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(db1);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MybatisPlusConfig.MAPPER_LOCATION));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean("dbTransactionManager")
    @Override
    public TransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(this.druidDataSource1());
    }

    /**
     * 分页插件
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 数据库的配置DbType.ORACLE 根据自己的数据库选择
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}

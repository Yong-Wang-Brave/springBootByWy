package com.wy.demo.mybatis.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.wy.demo.Druid.dto.MysqlDruidDataSourceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@Slf4j
public class DataSourceConfig {

    @Resource
    MysqlDruidDataSourceProperties mysqlDruidDataSourceProperties;
    //mysql数据源
    @Bean(name="msDataSource")
    public DataSource msDataSource(){
        log.info("开始加载mysql druid");
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(mysqlDruidDataSourceProperties.getDriverClassname());
        druidDataSource.setUrl(mysqlDruidDataSourceProperties.getUrl());
        druidDataSource.setUsername(mysqlDruidDataSourceProperties.getUserName());
        druidDataSource.setPassword(mysqlDruidDataSourceProperties.getPwd());
        druidDataSource.setInitialSize(mysqlDruidDataSourceProperties.getInitialSize());
        druidDataSource.setMinIdle(mysqlDruidDataSourceProperties.getMinIdle());
        druidDataSource.setMaxActive(mysqlDruidDataSourceProperties.getMaxActive());
        druidDataSource.setMaxWait(mysqlDruidDataSourceProperties.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(mysqlDruidDataSourceProperties.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(mysqlDruidDataSourceProperties.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(mysqlDruidDataSourceProperties.getValidationQuery());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(mysqlDruidDataSourceProperties.getMaxPoolPreparedStatementPerConnectionSize());
        druidDataSource.setTestWhileIdle(mysqlDruidDataSourceProperties.getTestWhileIdle());
        druidDataSource.setTestOnBorrow(mysqlDruidDataSourceProperties.getTestOnBorrow());
        druidDataSource.setTestOnReturn(mysqlDruidDataSourceProperties.getTestOnReturn());
        druidDataSource.setPoolPreparedStatements(mysqlDruidDataSourceProperties.getPoolPreparedStatements());
        log.info("mysql的druid加载完成");
        return druidDataSource;
    }


}

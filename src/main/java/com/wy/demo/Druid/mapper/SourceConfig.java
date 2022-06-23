package com.wy.demo.Druid.mapper;


import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import com.wy.demo.Druid.dto.MysqlDruidDataSourceProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
@Configuration
@MapperScan(basePackages = "com.wy.demo.*" ,sqlSessionFactoryRef = "SqlSessionFactory")
public class SourceConfig {
@Resource
    MysqlDruidDataSourceProperties mysqlDruidDataSourceProperties;
    //mysql数据源
    //表述这个数据源是默认数据源
    @Primary
    @Bean(name="DataSource")
    public DataSource tiDataSource(){
        log.info("开始加载mysql druid");
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(mysqlDruidDataSourceProperties.getDriverClassname());
        druidDataSource.setUrl(mysqlDruidDataSourceProperties.getPrimaryUrl());
        druidDataSource.setUsername(mysqlDruidDataSourceProperties.getUserName());
        druidDataSource.setPassword(mysqlDruidDataSourceProperties.getPwd());
        log.info("mysql的druid加载完成");
        return druidDataSource;
    }
    @Primary
    @Bean (name="TransactionManager")
    public DataSourceTransactionManager tiTransactionManager() throws SQLException{
        return new DataSourceTransactionManager(tiDataSource());
    }
    @Primary
    @Bean(name="SqlSessionFactory")
    public SqlSessionFactory tiSqlSessionFactory(@Qualifier("DataSource") DataSource backDataSource) throws Exception{
      final  SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        //分页插件
        PageInterceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        //数据库
        properties.setProperty("helperDialect","mysql");
        //是否将参数offset作为pageNum使用
        properties.setProperty("offsetAsPageNum","true");
        //是否进行count
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("supportMethodsArguments","true");
        //是否分页合理化
        //注意的是reasonable参数，表示分页合理化，默认值是false
        //如果该参数设置的为true，pageNum<0会查询第一页 pageNum>pages(超过总数)，会查询最后一页，默认false参数进行插查询
        properties.setProperty("reasonable","false");
        interceptor.setProperties(properties);
        sessionFactory.setPlugins(new Interceptor[]{interceptor});
        sessionFactory.setDataSource(backDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(
                "classpath:/mybatis/mapper/*Mapper.xml"));
        SqlSessionFactory sqlSessionFactory =sessionFactory.getObject();
        sqlSessionFactory.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        sqlSessionFactory.getConfiguration().setCallSettersOnNulls(false);
        return sessionFactory.getObject();


    }
}

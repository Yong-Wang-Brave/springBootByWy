package com.wy.demo.Druid;


import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import com.wy.demo.Druid.dto.MysqlDruidDataSourceProperties;
import com.wy.demo.mybatiesInterceptor4.SqlCostInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j

//@Configuration
//@MapperScan(basePackages = "com.wy.demo.mybatis.mappers" ,sqlSessionFactoryRef = "healthRecordSqlSessionFactory")
public class HealthRecordDataSourceConfig {
@Resource
    MysqlDruidDataSourceProperties mysqlDruidDataSourceProperties;
    //mysql数据源
    @Bean(name="healthRecordDataSource")
    public DataSource tiDataSource(){
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

    @Bean (name="healtnRecordTransactionManager")
    public DataSourceTransactionManager tiTransactionManager() throws SQLException{
        return new DataSourceTransactionManager(tiDataSource());
    }

    @Bean(name="healthRecordSqlSessionFactory")
    public SqlSessionFactory tiSqlSessionFactory(@Qualifier("healthRecordDataSource") DataSource backDataSource) throws Exception{
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
        sessionFactory.setPlugins(new Interceptor[]{interceptor,new SqlCostInterceptor()});
        sessionFactory.setDataSource(backDataSource);

        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(
                "classpath:/mybatis/mapper/*Mapper.xml"));
        SqlSessionFactory sqlSessionFactory =sessionFactory.getObject();
        sqlSessionFactory.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        sqlSessionFactory.getConfiguration().setCallSettersOnNulls(false);
        return sessionFactory.getObject();


    }


  /*  @Bean(name="RoutingDataSource")
    @Order(11)
    public RoutingDataSource getSlave(@Qualifier("healthRecordDataSource") DataSource backDataSource) throws SQLException{
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("master",backDataSource);
        objectObjectHashMap.put("slave",backDataSource);
        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setTargetDataSources(objectObjectHashMap);
        Class clazz = ClassLoader.loadClass("org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource");

        Method method = clazz.getDeclaredMethod("determineCurrentLookupKey", null);

        method.setAccessible(true);

        method.invoke(cat, null);

        return routingDataSource;
    }*/
}

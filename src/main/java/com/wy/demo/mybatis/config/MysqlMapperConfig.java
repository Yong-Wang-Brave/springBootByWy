package com.wy.demo.mybatis.config;

import com.wy.demo.mybatiesInterceptor4.SqlCostInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.wy.demo.*.mappers"}, sqlSessionFactoryRef = "msSqlSessionFactory")
public class MysqlMapperConfig {
    @Autowired
    @Qualifier("msDataSource")
    private DataSource msDataSource;


    @Bean("msSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(msDataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/**/*Mapper.xml"));
        //拦截器打印sql,极耗性能，慎开
        factoryBean.setPlugins((new Interceptor[]{new SqlCostInterceptor()}));
        SqlSessionFactory sqlSessionFactory = factoryBean.getObject();
        assert sqlSessionFactory != null;
        //避免在insert,实体类字段为null,报错问题
        sqlSessionFactory.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
        //开启驼峰匹配
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactory;
    }

@Bean(name="msTransactionManager")
    public DataSourceTransactionManager msTransactionManager(@Qualifier("msDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionTemplate usercenterSqlSessionTemplate(@Qualifier("msSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}

package com.wy.demo.mybatis.config;

import com.github.pagehelper.PageInterceptor;
import com.wy.demo.mybatiesInterceptor4.SqlCostInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = {"com.wy.demo.*.mappers"}, sqlSessionFactoryRef = "msSqlSessionFactory")
public class MysqlMapperConfig {
    @Autowired
    @Qualifier("msDataSource")
    private DataSource msDataSource;


    @Bean("msSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        //分页插件
        Interceptor interceptor = new PageInterceptor();
        Properties properties =new Properties();
        //数据库
        properties.setProperty("helperDialect","mysql");
        //是否将offset作为pagenNum使用
        properties.setProperty("OffsetAsPageNum","true");
        //是否进行count查询
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("supportMethodsArgumensts","true");
        //是否分页合理化
        //注意的是reasonable参数，表示分页合理化，默认值为false
        //如果该参数设置成true,pageNum<0时会查第一页，pageNum>pages（超过总数时）会查询最后一页，默认false时，直接根据参数进行查询
       properties.setProperty("reasonable","fasle");
        interceptor.setProperties(properties);

        factoryBean.setDataSource(msDataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/**/*Mapper.xml"));
        //拦截器打印sql,极耗性能，慎开
        factoryBean.setPlugins((new Interceptor[]{new SqlCostInterceptor(),interceptor}));
        SqlSessionFactory sqlSessionFactory = factoryBean.getObject();
        assert sqlSessionFactory != null;
        //避免在insert,实体类字段为null,报错问题
        sqlSessionFactory.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
        //开启驼峰匹配
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        sqlSessionFactory.getConfiguration().setCallSettersOnNulls(false);
        return sqlSessionFactory;
    }

@Bean(name="msTransactionManager")
@Primary
/**
 *  创建该数据源的事务管理器
 */
public DataSourceTransactionManager msTransactionManager(@Qualifier("msDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

  /*  @Bean
    public SqlSessionTemplate usercenterSqlSessionTemplate(@Qualifier("msSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }*/
}

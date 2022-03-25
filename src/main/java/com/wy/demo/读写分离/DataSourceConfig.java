package com.wy.demo.读写分离;


import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

  @Autowired
  private Props props;

  @Primary
  @Bean("master")
  DataSource masterDataSource() {
    return dataSource(props.getMasterUrl(), props.getMasterUsername(), props.getMasterPassword());
  }

  @Bean("slave")
  DataSource slaveDataSource() {
    return dataSource(props.getSlaveUrl(), props.getSlaveUsername(), props.getSlavePassword());
  }

  private DataSource dataSource(String url, String user, String pwd) {
    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setUrl(url);
    basicDataSource.setUsername(user);
    basicDataSource.setPassword(pwd);
    basicDataSource.setMaxTotal(24); // 最好不要超过cpu个数
    basicDataSource.setMaxIdle(5); // 连接池最大空闲数
    basicDataSource.setMinIdle(3); // 连接池最小空闲数
    basicDataSource.setInitialSize(10); // 初始化连接池时的连接数
    basicDataSource.setMaxConnLifetimeMillis(60000);
    basicDataSource.setRemoveAbandonedTimeout(30);
    return basicDataSource;
  }

  //分布式事务 XA
//  @Primary
//  @Bean("master")
//  DataSource masterDataSource() {
//    return atomicDataSource(props.getMasterUrl(),props.getMasterUsername(),props.getMasterPassword(),"master");
//  }
//  @Bean("slave")
//  DataSource slaveDataSource() {
//    return atomicDataSource(props.getSlaveUrl(),props.getSlaveUsername(),props.getSlavePassword(),"slave");
//  }
//  private AtomikosDataSourceBean atomicDataSource(String url, String user, String pwd,String name) {
//    AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
//    atomikosDataSourceBean.setUniqueResourceName(name);
//    atomikosDataSourceBean.setXaDataSourceClassName(
//        "com.mysql.cj.jdbc.MysqlXADataSource");
//    Properties properties = new Properties();
//    properties.put("URL",url);
//    properties.put("user", user);
//    properties.put("password", pwd);
//    atomikosDataSourceBean.setXaProperties(properties);
//    return atomikosDataSourceBean;
//  }
//动态数据源
  @Bean("dataSource")
  public RoutingDataSource dataSource(@Qualifier("master") DataSource master, @Qualifier("slave") DataSource slave) {
//    return new RoutingDataSource(dataSource(props.getMasterUrl(),props.getMasterUsername(),props.getMasterPassword()),dataSource(props.getSlaveUrl(),props.getSlaveUsername(),props.getSlavePassword()));
    return new RoutingDataSource(master, slave);
  }

  //定义mybatis的动态session
  @Bean
  SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") RoutingDataSource dataSource) {
    SqlSessionFactory sessionFactory = null;
    try {
      SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
      bean.setDataSource(dataSource);
      sessionFactory = bean.getObject();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return sessionFactory;
  }

}
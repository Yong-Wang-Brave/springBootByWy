package com.wy.demo.Druid.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "mysql.druid")
public class MysqlDruidDataSourceProperties {
    private String driverClassname;
    private Boolean testWhileIdle;
    private Boolean testOnBorrow;
    private Boolean testOnReturn;
    private String  validationQuery;
    private String filters;
    private Long  minEvictableIdleTimeMillis;
    private Integer InitialSize;
    private Integer minIdle;
    private Integer maxActive;
    private Long maxWait;
    private Long timeBetweenEvictionRunsMillis;
    private Boolean poolPreparedStatements;
    private Integer maxPoolPreparedStatementPerConnectionSize;
    private String primaryUrl;
    private String url;
    private String userName;
    private String pwd;
}

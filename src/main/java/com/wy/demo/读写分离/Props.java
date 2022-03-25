package com.wy.demo.读写分离;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author ys 2021/4/8
 * @description
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class Props {

  private String masterUrl;
  private String masterUsername;
  private String masterPassword;
  private String masterType;

  private String slaveUrl;
  private String slaveUsername;
  private String slavePassword;
  private String slaveType;
}

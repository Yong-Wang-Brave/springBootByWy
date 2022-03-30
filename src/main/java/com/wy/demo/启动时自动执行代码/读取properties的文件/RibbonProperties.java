package com.wy.demo.启动时自动执行代码.读取properties的文件;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "ribbon")
public class RibbonProperties {
  private String ConnectTimeout;
   //亲测 设置实践无效
   private String SocketTimeout;

//同一台实例最大的重试次数，不包括首次调用
private String MaxAutoRetries;
}

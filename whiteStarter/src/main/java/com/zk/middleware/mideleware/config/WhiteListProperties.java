package com.zk.middleware.mideleware.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("zk.whitelist") // 用于创建指定前缀( prefix = "bugstack.whitelist" )的自定义配置信息
public class WhiteListProperties {

   private String user;

   public String getUser() {return user;}

   public void setUser(String user) {this.user = user;}

}

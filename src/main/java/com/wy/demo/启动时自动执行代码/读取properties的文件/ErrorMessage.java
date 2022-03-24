package com.wy.demo.启动时自动执行代码.读取properties的文件;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
//配置文件
//#控制台显示sql
//logging.level.com.wy.demo.mapper=debug
//
//# 输出日志
//logging.level.com.my.wy.mybatis.mappers=trace

//转化的结果 ErrorMessage(level={com.wy.demo.mapper=debug, com.my.wy.mybatis.mappers=trace})
@Component
@ConfigurationProperties(prefix = "logging")
@Data
public class ErrorMessage {
   //level要和配置文件中的名字保持一致
   private Map<String,String> level= new HashMap<>();
}

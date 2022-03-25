package com.wy.demo.读写分离;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @Author ys 2021/4/8
 * @description  定义一个 holder用于存放当前线程的数据库名称,接下来就是定义切面了。
 */
@Aspect
@Component
public class DataSourceAspect {
  private final Logger logger = LoggerFactory.getLogger(getClass());

  private static final List<String> DATA_SOURCE_KEYS = Arrays.asList("master", "slave");


  @Before("@annotation(targetDataSource)")
  public void switchDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource){
    if (!DATA_SOURCE_KEYS.contains(targetDataSource.value())) {
      logger.error(
          "datasource [{}] doesn't exist, use default datasource [{}]", targetDataSource.value());
    } else {
      DataSourceContextHolder.setDataSourceKey(targetDataSource.value());
      logger.info(
          "switch datasource to [{}] in method [{}]",
          DataSourceContextHolder.getDataSourceKey(),
          joinPoint.getSignature());
    }
  }

  @After("@annotation(targetDataSource))")
  public void restoreDataSource(JoinPoint point, TargetDataSource targetDataSource) {
    DataSourceContextHolder.clearDataSourceKey();
    logger.info(
        "restore datasource to [{}] in method [{}]",
        DataSourceContextHolder.getDataSourceKey(),
        point.getSignature());
  }
}

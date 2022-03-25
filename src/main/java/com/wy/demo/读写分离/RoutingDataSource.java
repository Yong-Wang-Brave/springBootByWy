package com.wy.demo.读写分离;

import com.google.common.collect.ImmutableMap;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

/**
 * @Author ys 2021/4/8
 * @description  自定义动态数据源
 */
public class RoutingDataSource extends AbstractRoutingDataSource {

  public RoutingDataSource(DataSource master, DataSource slave) {
    setTargetDataSources(ImmutableMap.builder().put("master",master).put("slave",slave).build());
  }
  @Override
  protected Object determineCurrentLookupKey() {
    return DataSourceContextHolder.getDataSourceKey();
  }
}

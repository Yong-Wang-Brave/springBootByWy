package com.wy.demo.zhidingshujuyuan;

import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;


//@Component
@Order(22)
public class RoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceType = DataSourceHolder.getDataSourceType();
        return dataSourceType;
    }

    @Override
    public void setDefaultTargetDataSource(Object defaultTargetDataSource) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
    }

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
    }

    @Override
    public void afterPropertiesSet() {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("master","healthRecordDataSource");
        objectObjectHashMap.put("slave","healthRecordDataSource");

        this.setTargetDataSources(objectObjectHashMap);
        super.afterPropertiesSet();
    }
}

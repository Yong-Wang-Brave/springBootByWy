package com.wy.demo.controller;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Two {

    @GetMapping("/druidStat")
    public Object druidStat(){
        // 获取数据源的监控数据
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }
}
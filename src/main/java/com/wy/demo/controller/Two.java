package com.wy.demo.controller;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import com.wy.demo.thread.Async.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Two {
@Autowired
    AsyncService asyncService;
    @GetMapping("/druidStat")
    public Object druidStat(){
        // 获取数据源的监控数据
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }

    @GetMapping("/async")
    public void getAsync(){
         asyncService.async();
        System.out.println();
    }
}
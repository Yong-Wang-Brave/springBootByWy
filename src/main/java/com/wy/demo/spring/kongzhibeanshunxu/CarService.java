package com.wy.demo.spring.kongzhibeanshunxu;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
//@DependsOn(value = "systemInfoService")
//carService的加载依赖于systemInfoService，场景如果依赖多个怎么处理
public class CarService {

    @PostConstruct
    public void  init(){
        System.out.println("王勇1"+SystemInfoService.getKey("sortCourseName"));
    }
}

package com.wy.demo.spring.kongzhibeanshunxu;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ZhouyuService {

    @PostConstruct
    public void  init(){
    System.out.println( "王勇2"+SystemInfoService.getKey("sortCourseName"));
    }
}

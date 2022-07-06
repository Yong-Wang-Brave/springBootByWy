package com.wy.demo.shejimoshi.代理模式;

import java.util.Arrays;
import java.util.List;

public class RouterProxy implements Internet {//路由器代理类

     private Internet modem;//持有被代理类引用
     private List<String> blackList = Arrays.asList("电影", "游戏", "音乐", "小说");

     public RouterProxy() {
         this.modem = new Modem();//实例化被代理类
         System.out.println("拨号上网...连接成功！");
     }

    @Override
    public void access(String url) {//同样实现互联网访问接口方法
        for (String keyword : blackList) {//循环黑名单
            if (url.contains(keyword)) {//是否包含黑名单字眼
               System.out.println("禁止访问：" + url);
                return;
            }
        }
        modem.access(url);//正常访问互联网
    }
}
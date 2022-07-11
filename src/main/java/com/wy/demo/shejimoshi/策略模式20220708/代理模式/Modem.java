package com.wy.demo.shejimoshi.策略模式20220708.代理模式;

public class Modem implements Internet {//调制解调器

    @Override
    public void access(String url){//实现互联网访问接口
       System.out.println("正在访问：" + url);
    }
}
package com.wy.demo.shejimoshi.代理模式.升级;

public class Switch implements Intranet {

    @Override
    public void fileAccess(String path){
        System.out.println("访问内网：" + path);
    }

}
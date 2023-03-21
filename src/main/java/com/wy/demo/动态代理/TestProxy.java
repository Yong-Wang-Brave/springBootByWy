package com.wy.demo.动态代理;

public class TestProxy {

    public static void main(String[] args) {
        SuperStar star = new SuperStar("王心凌");
        Star proxy = ProxyUtils.createProxy(star);
        proxy.dance();
        proxy.sing("爱的天国");

    }
}

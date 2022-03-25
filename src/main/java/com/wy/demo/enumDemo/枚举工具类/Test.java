package com.wy.demo.enumDemo.枚举工具类;


public class Test {

    public static void main(String[] args) {
        LevelEnum ss = LevelEnum.getBeanByCode("FIRST");
        System.out.println(ss.getValue());
        LevelEnum2 first = LevelEnum2.getBeanByCode("FIRST");
        System.out.println(first.getValue());
    }
}

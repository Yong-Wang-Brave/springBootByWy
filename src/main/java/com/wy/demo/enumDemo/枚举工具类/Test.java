package com.wy.demo.enumDemo.枚举工具类;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

public class Test {

    public static void main(String[] args) {
        LevelEnum ss = LevelEnum.getBeanByCode("FIRST");
        System.out.println(ss.getValue());
        LevelEnum2 first = LevelEnum2.getBeanByCode("FIRST");
        System.out.println(first.getValue());
    }
}

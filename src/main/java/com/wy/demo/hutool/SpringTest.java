package com.wy.demo.hutool;

import cn.hutool.extra.spring.SpringUtil;

public class SpringTest {

    public static void main(String[] args) {
        String activeProfile = SpringUtil.getActiveProfile();
        System.out.println(activeProfile);
    }
}

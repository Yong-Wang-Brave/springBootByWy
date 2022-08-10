package com.wy.demo.date.hutool获取节气;

import cn.hutool.core.date.chinese.SolarTerms;

public class DateTest {
    public static void main(String[] args) {
        String term = SolarTerms.getTerm(2022, 8, 23);
        System.out.println(term);
    }
}

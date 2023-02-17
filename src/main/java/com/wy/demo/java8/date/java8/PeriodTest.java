package com.wy.demo.java8.date.java8;

import java.time.LocalDate;
import java.time.Period;

public class PeriodTest {
    public static void main(String[] args) {
        LocalDate start = LocalDate.of(2029, 11, 5);
        LocalDate end = LocalDate.of(2029, 11, 15);
        //计算两个日期之间的间隔
        Period between = Period.between(start, end);
        System.out.println(between.getYears());
        System.out.println(between.getMonths());
        System.out.println(between.getDays());

    }
}

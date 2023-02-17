package com.wy.demo.java8.date.java8以前;

import java.util.Date;

public class BeforeJave8Date {
    public static void main(String[] args) {
        Date d = new Date();
        System.out.println(d);

        long time = d.getTime();
        //返回从1970年1月1日 00：:00:00到此刻的毫秒数
        System.out.println(time);

        time+=2*1000;
        Date d2 = new Date(time);
        System.out.println(d2);

        Date d3 = new Date();
        d3.setTime(time);
        //返回毫秒值为对对应的日期
        System.out.println(d3);


    }
}

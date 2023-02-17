package com.wy.demo.java8.date.java8以前;

import java.util.Calendar;
import java.util.Date;

public class BeforeJava8Calendar {
    public static void main(String[] args) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.HOUR,12);
        System.out.println(now);
        //获取日历的某年
        int year = now.get(Calendar.YEAR);
        System.out.println(year);

        int days = now.get(Calendar.DAY_OF_YEAR);
        System.out.println(days);
        //日历转date
        Date d = now.getTime();
        System.out.println(d);
        //日历转long
        long timeInMillis = now.getTimeInMillis();
        System.out.println(timeInMillis);

        //日历设置日期
        now.set(Calendar.MONTH,9);//修改月份为10月份
        now.set(Calendar.DAY_OF_YEAR,125);//修改成一年的125天
        System.out.println(now.getTime());

        now.add(Calendar.DAY_OF_YEAR,100);
        now.add(Calendar.DAY_OF_MONTH,6);
        now.add(Calendar.HOUR,12);
        System.out.println(now.getTime());




    }
}

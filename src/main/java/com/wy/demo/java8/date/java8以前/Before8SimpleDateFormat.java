package com.wy.demo.java8.date.java8以前;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Before8SimpleDateFormat {

    public static void main(String[] args) throws ParseException {
        Date d = new Date();
        long time = d.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss EEE a");
        String rs = sdf.format(d);
        System.out.println(rs);
        //将毫秒值格式化为日期
        String rs2 = sdf.format(time);
        System.out.println(rs2);

        String dataStr = "2022-12-12 12:12:12";
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date parse = sdf2.parse(dataStr);
        System.out.println(parse);

    }



}

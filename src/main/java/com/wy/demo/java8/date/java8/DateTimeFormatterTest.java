package com.wy.demo.java8.date.java8;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterTest {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");

        LocalDateTime now = LocalDateTime.now();
        String format = formatter.format(now);
        System.out.println(format);
        String format1 = now.format(formatter);
        System.out.println(format1);
        String dateStr ="2029年12月12日 12:12:11";
        LocalDateTime parse = LocalDateTime.parse(dateStr, formatter);
        System.out.println(parse);
    }
}

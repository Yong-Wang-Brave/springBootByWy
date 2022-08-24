package com.wy.demo.date.hutool获取节气;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.date.chinese.SolarTerms;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTest {
    public static void main(String[] args) {
        String term = SolarTerms.getTerm(2022, 8, 23);
        System.out.println(term);

        String format = DateUtil.format(new Date(), "MM/dd");
        System.out.println(format);
        DateTime dateTime = DateUtil.beginOfMonth(new Date());
        DateTime dateTimeEnd = DateUtil.endOfMonth(new Date());
        System.out.println(dateTime);
        System.out.println(dateTimeEnd);
        String dateStr="2022-09-22";

        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        String s = LocalDateTimeUtil.dayOfWeek(date).toChinese();
        System.out.println(s);


    }
}

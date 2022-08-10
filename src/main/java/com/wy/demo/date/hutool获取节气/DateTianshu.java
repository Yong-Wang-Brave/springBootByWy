package com.wy.demo.date.hutool获取节气;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateTianshu {
    public static void main(String[] args) throws ParseException {

        String start  = "2018-05-10";

        int  number  = 30;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date startTime = dateFormat.parse(start);


Calendar startCalendar = Calendar.getInstance();

startCalendar.setTime(startTime);

ArrayList<String> dates = new ArrayList<String>();


        dates.add(dateFormat.format(startCalendar.getTime()));

 for ( int i = 0 ; i < number; i++) {

            startCalendar.add(Calendar.DAY_OF_MONTH, 1);
            dates.add(dateFormat.format(startCalendar.getTime()));
        }
        System.out.println(dates);
    //    https://blog.51cto.com/u_15354478/3762369
    }
}

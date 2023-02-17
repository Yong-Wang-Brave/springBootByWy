package com.wy.demo.java8.date.java8;

import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZoneIdTest {
    public static void main(String[] args) {
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println(zoneId);
        System.out.println(zoneId.getId());
        //获取所有的时区
        System.out.println(ZoneId.getAvailableZoneIds());
        ZoneId zoneId1 = ZoneId.of("America/New_York");

        ZonedDateTime now = ZonedDateTime.now(zoneId1);
        System.out.println(now);

        ZonedDateTime now1 = ZonedDateTime.now(Clock.systemUTC());
        System.out.println(now1);
        System.out.println(ZonedDateTime.now());


    }
}

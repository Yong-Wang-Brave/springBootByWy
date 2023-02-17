package com.wy.demo.java8.date.java8;

import java.time.Instant;

public class InstanTest {
    public static void main(String[] args) {
        Instant now = Instant.now();
        System.out.println(now);
        long second = now.getEpochSecond();
        System.out.println(second);

        int nano = now.getNano();
        Instant instant = now.plusNanos(111);

    }
}

package com.wy.demo.优化.统计耗时;


import org.springframework.util.StopWatch;

import java.util.Date;

/**
 * 统计耗时
 */
public class TestTime {

    public static void main(String[] args) throws InterruptedException {
        testOne();
        testTwo();
        testThree();
        testFour();
        testFive();
        task();
    }

    private static void testFive() throws InterruptedException {
        long l = System.currentTimeMillis();
        task();
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l);
    }

    private static void testFour() throws InterruptedException {
        Date date = new Date();
        task();
        Date date2 = new Date();
        System.out.println(date2.getTime()-date.getTime());

    }

    private static void testThree() throws InterruptedException {
        long l = System.nanoTime();
        task();
        long l1 = System.nanoTime();
        System.out.println(l1-1);
    }

    private static void testTwo() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        task();
        stopWatch.stop();
        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        System.out.println(totalTimeMillis);
    }

    private static void task() throws InterruptedException {
      Thread.sleep(2000);
    }

    private static void testOne() throws InterruptedException {
        long l = System.currentTimeMillis();
        task();
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l);
    }
}

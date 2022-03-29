package com.wy.demo.内存溢出;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class OverDemo {
    private  static final  int NUM=1024;
//内存泄漏是该回收的没回收
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Object> list = Lists.newArrayList();
        for (int i = 0; i <NUM ; i++) {
            TimeUnit.SECONDS.sleep(1);
            list.add(new byte[NUM*NUM]);
        }

    }

}

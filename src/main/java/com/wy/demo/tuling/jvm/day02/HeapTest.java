package com.wy.demo.tuling.jvm.day02;

import java.util.ArrayList;

/**
 * 堆溢出
 */
public class HeapTest {
    byte[] a =new byte[1024*100]; //100KB
    public static void main(String[] args) throws InterruptedException {
        //堆溢出代码
        ArrayList<HeapTest> heapTests =new ArrayList<>();
        while(true){

            heapTests.add(new HeapTest());
                Thread.sleep(10);
        }

    }

}

package com.wy.demo.errorOneHundred.e02.e01;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class aAtEST {
    public static void main(String[] args) {
        ConcurrentHashMap<String, LongAdder> freqs = new ConcurrentHashMap<>(10);


      IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
          String key = "item" + ThreadLocalRandom.current().nextInt(10);
          freqs.computeIfAbsent(key, k -> new LongAdder()).increment();
      });
        System.out.println(freqs);
}
}
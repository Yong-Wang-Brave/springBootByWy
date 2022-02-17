package com.wy.demo.error100.day02;

import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * 有一个含 900 个元素的
 * Map，现在再补充 100 个元素进去，这个补充操作由 10 个线程并发进行。
 */
@Slf4j
public class Put100 {
    //线程个数
    private static int THREAD_COUNT = 10;
    //总元素数量
    private static int ITEM_COUNT = 1000;
//帮助方法，用来获得一个指定元素数量模拟数据的ConcurrentHashMap
private static ConcurrentHashMap<String, Long> getData(int count) {
    return LongStream.rangeClosed(1, count)
            .boxed()
            .collect(Collectors.toConcurrentMap(i -> UUID.randomUUID().toString(), Function.identity(),
                    (o1, o2) -> o2, ConcurrentHashMap::new));
}

    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, Long> data = getData(ITEM_COUNT - 100);
        ForkJoinPool fp =new ForkJoinPool(10);
        fp.execute(()-> IntStream.rangeClosed(1,10).parallel().forEach(
                i->
                {     synchronized(data){
                    int gap=1000-data.size();
                    log.info("gap size:{}", gap);
                    data.putAll(getData(gap));
                }
                }
        ));
        fp.shutdown();
        fp.awaitTermination(1,TimeUnit.HOURS);
        log.info("finish size:{}", data.size());


      //  System.out.println(ThreadLocalRandom.current().nextInt(THREAD_COUNT));
    }
}



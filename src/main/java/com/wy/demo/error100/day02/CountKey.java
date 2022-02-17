package com.wy.demo.error100.day02;

import cn.hutool.core.lang.Assert;
import io.lettuce.core.ScriptOutputType;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 使用 ConcurrentHashMap 来统计，Key 的范围是 10。
 * 使用最多 10 个并发，循环操作 1000 万次，每次操作累加随机的 Key。
 * 如果 Key 不存在的话，首次设置值为 1。
 */
public class CountKey {
    private static int  num =10;
    private static int  key1 =10;

    public static void main(String[] args) throws InterruptedException {
        //统计性能
        StopWatch sw =new StopWatch();
        sw.start("normaluse");
        Map<String, Long> stringLongMap = nolmalUse();
        //map如何累加所有的value值
        Assert.isTrue(stringLongMap.entrySet().stream().mapToLong(t->t.getValue()).reduce(0,Long::sum)==10000000,"error");
        sw.stop();
        sw.start("gooduse");
        Map<String, Long> goodUse = goodUse();
        sw.stop();
        System.out.println(sw.prettyPrint());


    }


    public static   Map<String, Long> nolmalUse() throws InterruptedException {
        ConcurrentHashMap<String, Long> concurrentHashMap = new ConcurrentHashMap<>(10);
        ForkJoinPool fp =new ForkJoinPool(num);
        fp.execute(()->
            IntStream.rangeClosed(1,10000000).parallel().forEach(

                    i->{
                        String key ="Item"+ ThreadLocalRandom.current().nextInt(key1);
                        synchronized (concurrentHashMap){

                        if (concurrentHashMap.containsKey(key)) {
                            concurrentHashMap.put(key,concurrentHashMap.get(key)+1l);
                        }else{
                            concurrentHashMap.put(key,1l);
                        }
                    }
                    }));

        fp.shutdown();
        fp.awaitTermination(1, TimeUnit.HOURS);
        System.out.println(concurrentHashMap);
        return concurrentHashMap;

    };



    public static   Map<String, Long> goodUse() throws InterruptedException {
      ConcurrentHashMap<String, LongAdder> concurrentHashMap = new ConcurrentHashMap<>();
      ForkJoinPool fp =new ForkJoinPool(num);
      fp.execute(()->{
          IntStream.rangeClosed(1,10000000).parallel().forEach(

                  i->{

                      String key ="Item"+ ThreadLocalRandom.current().nextInt(key1);

                      concurrentHashMap.computeIfAbsent(key,k->new LongAdder()).increment();
                  }
          );
      });
      fp.shutdown();
      fp.awaitTermination(1, TimeUnit.HOURS);
      Map<String, Long> collect = concurrentHashMap.entrySet().stream().collect(
              Collectors.toMap(
                      e -> e.getKey(),
                      e -> e.getValue().longValue()
              )
      );
      System.out.println(collect);
 return collect;

  };




}

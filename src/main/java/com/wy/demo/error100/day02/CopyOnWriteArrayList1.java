package com.wy.demo.error100.day02;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CopyOnWriteArrayList1 {

    public static void main(String[] args) {
        write();
        read();
    }
private static void addAll(List<Integer>  list){
        list.addAll(IntStream.rangeClosed(1,100000).boxed().collect(Collectors.toList()));
};
    private static void read() {
        List<Integer> objects = new CopyOnWriteArrayList<>();
        List<Integer> objects1 = Collections.synchronizedList(new ArrayList<>());
      addAll(objects);
      addAll(objects1);
        StopWatch sp = new StopWatch();
        sp.start("copy读");
        IntStream.rangeClosed(1,100000).parallel().forEach(__->objects.get(ThreadLocalRandom.current().nextInt(100000)));
        sp.stop();
        sp.start("list读");
        IntStream.rangeClosed(1,100000).parallel().forEach(__->objects1.get(ThreadLocalRandom.current().nextInt(100000)));
        sp.stop();
        System.out.println(sp.prettyPrint());


    }

    private static void write() {
        List<Integer> objects = new CopyOnWriteArrayList<>();
        List<Integer> objects1 = Collections.synchronizedList(new ArrayList<>());
        StopWatch sp = new StopWatch();
        sp.start("copy复制写");
        IntStream.rangeClosed(1, 100000).parallel().forEach(
                __ -> objects.add(ThreadLocalRandom.current().nextInt(100000)));
        sp.stop();
        sp.start("arrayList");
        IntStream.rangeClosed(1,100000).parallel().forEach(
                __->objects1.add(ThreadLocalRandom.current().nextInt(100000)));
            sp.stop();
        System.out.println(    sp.prettyPrint());


    }


}

package com.wy.demo.java8.FlatMap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * map的1对1的关系，一个元素返回一个改变后的元素
 * flatMap是1对多的关系  一个元素返回多个改变后的元素。
 */
public class FlatMapTest {
    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("n-i", "h-a-o");
        List<String> collect = stringList.stream().flatMap(s -> {
            String[] split = s.split("-");
            Stream<String> stream = Arrays.stream(split);
            return stream;
        }).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println("...........................");
        final List<Integer> collect1 = Stream.of(1, 3, 4, 5, 2).collect(Collectors.toList());
        final List<Integer> doubleCollect = collect1.stream()
                .flatMap(k -> Stream.of(k, k))
                .collect(Collectors.toList());
        System.out.println(doubleCollect);

    }
}

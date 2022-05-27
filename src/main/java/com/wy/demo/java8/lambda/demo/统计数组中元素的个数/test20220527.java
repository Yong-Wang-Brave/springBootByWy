package com.wy.demo.java8.lambda.demo.统计数组中元素的个数;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class test20220527 {

    public static void main(String[] args) {
        String[] arr = {"a", "c", "a", "b", "d", "c"};
        Map<String, Integer> map = new HashMap<>();
        for (String s : arr) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }
        map.forEach((key, value) -> System.out.println(key + " : " + value));
        //方式二
        String[] arr1 = {"a", "c", "a", "b", "d", "c"};
        Map<String, Integer> map1 = new HashMap<>();
        for (String s : arr1) {
            map1.put(s, map1.getOrDefault(s, 0) + 1);
        }
        map.forEach((key, value) -> System.out.println(key + " : " + value));
    //方式三
        String[] arr2 = {"a", "c", "a", "b", "d", "c"};
        Stream.of(arr2)
                .collect(Collectors.toMap(k -> k, k -> 1, Integer::sum))
                .forEach((k, v) -> System.out.println(k + " : " + v));
        //注意
/*
        在上面的代码中，Collectors.toMap(k -> k, k -> 1, Integer::sum)这一部分可能不好理解，对于这里面的三个参数，第一个参数代表将arr中的每一个元素作为Map中的key，第二个参数代表每一个key所对应的value，在这里每一个元素都对应个数1，第三个参数代表，如果存在相同的key，该如何进行合并，这里通过使用Integer::sum，代表将具有相同key的元素进行合并时，其value进行相加，这样便实现了每个元素个数的统计。
*/

    }

}

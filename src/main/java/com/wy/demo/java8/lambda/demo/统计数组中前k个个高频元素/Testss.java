package com.wy.demo.java8.lambda.demo.统计数组中前k个个高频元素;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
//统计出出现频率最高的
public class Testss {
    public static void main(String[] args) {
        int[] arr = {1, 1,2,3,4,3,4,3,5,6,3};
        int[] ints = topKFrequent(arr, 10);
        System.out.println(Arrays.toString(ints));
    }
    public static int[] topKFrequent(int[] nums, int k) {
        return Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum))
                .entrySet()
                .stream()
                .sorted((m1, m2) -> m2.getValue() - m1.getValue())
                .limit(k)
                .mapToInt(Map.Entry::getKey)
                .toArray();

    }
}

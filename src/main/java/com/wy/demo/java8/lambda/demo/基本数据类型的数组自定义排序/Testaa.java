package com.wy.demo.java8.lambda.demo.基本数据类型的数组自定义排序;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;
//https://mp.weixin.qq.com/s/vt1nwKxxYea6J4INULyekA
public class Testaa {
    public static void main(String[] args) {
        int[] arr = {1, 5, 9, 7, 2, 3, 7, -1, 0, 3};
        int[] arr1 = IntStream.of(arr)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
        arr = arr1;
        System.out.println(Arrays.toString(arr));
    }
}

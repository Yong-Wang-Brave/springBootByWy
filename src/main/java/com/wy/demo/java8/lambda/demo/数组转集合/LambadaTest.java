package com.wy.demo.java8.lambda.demo.数组转集合;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
//https://mp.weixin.qq.com/s/vt1nwKxxYea6J4INULyekA
public class LambadaTest {
    public static void main(String[] args) {
        // 将 List 元素存储到数组中
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        int[] arr = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println(arr);
// 将数组元素 存储到 List 中
        int[] arr1 = {1, 2, 3, 4, 5};
        List<Integer> list1 = IntStream.of(arr1).boxed().collect(Collectors.toList());
        System.out.println(list1
        );
/*
boxed的作⽤就是将int类型的stream转成了Integer类型的Stream。
        --------------------------------------------------------
                作者：韦陀春叶13
        链接：https://wenku.baidu.com/view/95c6b45aa16925c52cc58bd63186bceb19e8edf0.html
        来源：百度文库
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/






    }
}

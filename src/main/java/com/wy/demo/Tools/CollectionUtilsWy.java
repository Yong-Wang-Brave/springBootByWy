package com.wy.demo.Tools;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;

public class CollectionUtilsWy {
    public static void main(String[] args) {
        // 判断 List/Set 是否为空
// 判断 Map 是否为空
        ArrayList<Object> objects = new ArrayList<>();
        objects.add("aa");
        objects.add("bb");
        ArrayList<Object> objects1 = new ArrayList<>();
        objects1.add("aa");
        boolean empty = CollectionUtils.isEmpty(objects);
        System.out.println(empty);


// 判断 List/Set 中是否包含某个对象
        boolean aa1 = CollectionUtils.containsInstance(objects, "aa");
// 以迭代器的方式，判断 List/Set 中是否包含某个对象
        boolean aa = CollectionUtils.contains(objects.iterator(), "aa");
// 判断 List/Set 是否包含某些对象中的任意一个
        boolean b = CollectionUtils.containsAny(objects, objects1);
        System.out.println(b);
// 判断 List/Set 中的每个元素是否唯一。即 List/Set 中不存在重复元素
        boolean b1 = CollectionUtils.hasUniqueObject(objects1);
        System.out.println(b1);
    }
}

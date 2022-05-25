package com.wy.demo.Tools;

import org.springframework.util.ObjectUtils;

import java.util.Arrays;

public class ObjectUtilsWy {
    public static void main(String[] args) {
        // 判断数组中是否包含指定元素
        boolean b = ObjectUtils.containsElement(new Object[]{1, 23, 3}, 3);
        System.out.println(b);
    }
}

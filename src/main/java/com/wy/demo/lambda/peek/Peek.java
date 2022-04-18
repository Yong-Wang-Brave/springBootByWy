package com.wy.demo.lambda.peek;

import java.util.stream.Collectors;
import java.util.stream.Stream;
//peek 返回值是和之前流泛型相同的流，多用于打印中间操作时的元素数据
//map可以操作元素
//foreach没有返回值，无法继续使用点语法操作
public class Peek {
    public static void main(String[] args) {
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .map(s->s+1)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

    }
}
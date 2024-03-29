package com.wy.demo.shejimoshi.责任链职责链模式20221012;

import java.util.function.Consumer;

public class TestMain {
    public static void main(String[] args) {
        test2();
    }
    static void test1() {
        Processor p1 = new ProcessorImpl1(null);
        Processor p2 = new ProcessorImpl2(p1);
        p2.process("something happened");
    }

    static void test2() {
        Consumer<String> p1 = param -> System.out.println("processor 1 is processing:" + param);
        Consumer<String> p2 = param -> System.out.println("processor 2 is processing:" + param);
        p2.andThen(p1).accept("something happened");
    }
}

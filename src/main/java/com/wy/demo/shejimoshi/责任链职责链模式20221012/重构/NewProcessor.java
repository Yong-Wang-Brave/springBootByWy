package com.wy.demo.shejimoshi.责任链职责链模式20221012.重构;

import java.util.function.Consumer;

@FunctionalInterface
public interface NewProcessor {
    Consumer<String> process(String param);
}
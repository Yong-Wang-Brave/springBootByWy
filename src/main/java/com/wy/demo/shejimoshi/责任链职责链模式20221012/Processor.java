package com.wy.demo.shejimoshi.责任链职责链模式20221012;

public interface Processor {

    Processor getNextProcessor();

    void process(String param);
}
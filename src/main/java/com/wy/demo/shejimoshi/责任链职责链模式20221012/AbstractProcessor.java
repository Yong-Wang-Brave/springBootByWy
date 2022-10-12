package com.wy.demo.shejimoshi.责任链职责链模式20221012;

public abstract class AbstractProcessor implements Processor {

    private Processor next;

    public AbstractProcessor(Processor processor) {
        this.next = processor;
    }

    @Override
    public Processor getNextProcessor() {
        return next;
    }

    @Override
    public abstract void process(String param);
}
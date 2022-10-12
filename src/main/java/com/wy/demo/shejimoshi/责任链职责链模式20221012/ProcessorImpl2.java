package com.wy.demo.shejimoshi.责任链职责链模式20221012;

public class ProcessorImpl2 extends AbstractProcessor {

    public ProcessorImpl2(Processor next) {
        super(next);
    }

    @Override
    public void process(String param) {
        System.out.println("processor 2 is processing:" + param);
        if (getNextProcessor() != null) {
            getNextProcessor().process(param);
        }
    }
}
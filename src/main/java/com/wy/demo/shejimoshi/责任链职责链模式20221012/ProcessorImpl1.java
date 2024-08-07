package com.wy.demo.shejimoshi.责任链职责链模式20221012;

public class ProcessorImpl1 extends AbstractProcessor {

    public ProcessorImpl1(Processor processor) {
        super(processor);
    }

    @Override
    public void process(String param) {
        System.out.println("processor 1 is processing:" + param);
        if (getNextProcessor() != null) {
            getNextProcessor().process(param);
        }
    }
}
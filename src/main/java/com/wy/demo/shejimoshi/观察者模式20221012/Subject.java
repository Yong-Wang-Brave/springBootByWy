package com.wy.demo.shejimoshi.观察者模式20221012;

public interface Subject {
    void registerObserver(Observer o);
    void notifyAllObserver(String orderNo);
}
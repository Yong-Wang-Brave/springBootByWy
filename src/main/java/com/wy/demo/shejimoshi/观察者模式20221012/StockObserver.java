package com.wy.demo.shejimoshi.观察者模式20221012;

public class StockObserver implements Observer {
    @Override
    public void notify(String orderNo) {
        System.out.println("订单 " + orderNo + " 已通知库房发货！");
    }
}
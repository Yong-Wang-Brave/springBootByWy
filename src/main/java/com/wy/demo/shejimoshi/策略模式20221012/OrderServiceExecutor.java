package com.wy.demo.shejimoshi.策略模式20221012;

public class OrderServiceExecutor {

    private final OrderService service;

    public OrderServiceExecutor(OrderService service) {
        this.service = service;
    }

    public void save(String orderNo) {
        this.service.saveOrder(orderNo);
    }

}
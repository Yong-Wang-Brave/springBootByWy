package com.wy.demo.shejimoshi.策略模式20221012;

public class NoSqlSaveOrderStrategy implements OrderService {
    @Override
    public void saveOrder(String orderNo) {
        System.out.println("order:" + orderNo + " save to nosql");
    }
}
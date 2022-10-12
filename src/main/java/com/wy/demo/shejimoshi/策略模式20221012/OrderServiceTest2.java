package com.wy.demo.shejimoshi.策略模式20221012;

public class OrderServiceTest2 {





    public static void main(String[] args) {
        OrderServiceExecutor executor1 = new OrderServiceExecutor((String orderNo) -> System.out.println("order:" + orderNo + " save to mysql"));
        executor1.save("001");

        OrderServiceExecutor executor2 = new OrderServiceExecutor((String orderNo) -> System.out.println("order:" + orderNo + " save to nosql"));
        executor2.save("002");
    }
}
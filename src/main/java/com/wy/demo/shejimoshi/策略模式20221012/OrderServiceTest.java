package com.wy.demo.shejimoshi.策略模式20221012;

public class OrderServiceTest {
    public static void main(String[] args) {
        OrderServiceExecutor executor1 = new OrderServiceExecutor(new MySqlSaveOrderStrategy());
        executor1.save("001");
        OrderServiceExecutor executor2 = new OrderServiceExecutor(new NoSqlSaveOrderStrategy());
        executor2.save("002");
    }





}
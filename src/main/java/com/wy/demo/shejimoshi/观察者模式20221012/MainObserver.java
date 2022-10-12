package com.wy.demo.shejimoshi.观察者模式20221012;

import com.wy.demo.shejimoshi.观察者模式20221012.方法2.NewSubject;

public class MainObserver {


    public static void main(String[] args) {
        test1();
    }
    static void test1() {
        Subject subject = new SubjectImpl();
        subject.registerObserver(new OrderObserver());
        subject.registerObserver(new StockObserver());
        subject.notifyAllObserver("001");
    }


    static void test2() {
        NewSubject subject = new NewSubject() {
        };
        subject.registerObserver((String orderNo) -> System.out.println("订单 " + orderNo + " 状态更新为【已支付】"));
        subject.registerObserver((String orderNo) -> System.out.println("订单 " + orderNo + " 已通知库房发货！"));
        subject.nofityAllObserver("002");
    }
}

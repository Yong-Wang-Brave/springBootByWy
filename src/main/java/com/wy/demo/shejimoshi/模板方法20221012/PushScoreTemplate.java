package com.wy.demo.shejimoshi.模板方法20221012;

public class PushScoreTemplate extends AbstractPushTemplate {

    @Override
    protected void execute(int customerId, String shopName) {
        System.out.println("会员:" + customerId + ",你好，" + shopName + "送您10个积分");
    }
}
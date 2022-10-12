package com.wy.demo.shejimoshi.模板方法20221012;

public abstract class AbstractPushTemplate {

    public void push(int customerId, String shopName) {
        System.out.println("准备推送...");
        execute(customerId, shopName);
        System.out.println("推送完成\n");
    }

    abstract protected void execute(int customerId, String shopName);
}
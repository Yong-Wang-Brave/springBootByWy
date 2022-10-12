package com.wy.demo.shejimoshi.模板方法20221012;

public class TempMain {
    public static void main(String[] args) {
        AbstractPushTemplate template1 = new PushCouponTemplate();
        template1.push(1, "糖果店");

        AbstractPushTemplate template2 = new PushScoreTemplate();
        template2.push(1, "服装店");
    }




}

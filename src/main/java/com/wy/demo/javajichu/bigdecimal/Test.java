package com.wy.demo.javajichu.bigdecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Test {
    public static void main(String[] args) {
        System.out.println(new BigDecimal(0.1).add(new BigDecimal(0.2)));
        System.out.println(new BigDecimal(1.0).subtract(new BigDecimal(0.8)));
        System.out.println(new BigDecimal(4.015).multiply(new BigDecimal(100)));
        System.out.println(new BigDecimal(123.3).divide(new BigDecimal(100)));
        //使用 BigDecimal 表示和计算浮点数，且务必使用字符串的构造方法来初始化
        //BigDecimal：
        System.out.println(new BigDecimal("0.1").add(new BigDecimal("0.2")));
        System.out.println(new BigDecimal("1.0").subtract(new BigDecimal("0.8")));
        //401.500
        System.out.println(new BigDecimal("4.015").multiply(new BigDecimal("100")));
        System.out.println(new BigDecimal("123.3").divide(new BigDecimal("100")));

        //用法
        BigDecimal divide = new BigDecimal("3").divide(new BigDecimal("7"), 2, BigDecimal.ROUND_HALF_UP);
        System.out.println(divide);

        //401.5000
        System.out.println(new BigDecimal("4.015").multiply(new BigDecimal(Double.toString(100))));
        //官方推荐  401.500
        System.out.println(new BigDecimal("4.015").multiply(BigDecimal.valueOf(100)));

          //源码是half_up
        System.out.println(String.format("%.1f", 2.35));
         //其他变种写法
        DecimalFormat format = new DecimalFormat("#.#");
         format.setRoundingMode(RoundingMode.DOWN);
         System.out.println(format.format(2.35));
         format.setRoundingMode(RoundingMode.HALF_UP);
         System.out.println(format.format(2.35));
     //避坑  浮点数的字符串格式化也要通过BigDecimal 进行

         BigDecimal num1 = new BigDecimal("3.35");
         BigDecimal num2 = num1.setScale(1, BigDecimal.ROUND_DOWN);
         System.out.println(num2);
         BigDecimal num3 = num1.setScale(1, BigDecimal.ROUND_HALF_UP);



        System.out.println(new BigDecimal("1.0").equals(new BigDecimal("1")));
    /*    BigDecimal 的 equals 方法的注释中说
        明了原因，equals 比较的是 BigDecimal 的 value 和 scale，1.0 的 scale 是 1，1 的
        scale 是 0，所以结果一定是 false：*/

        //如果我们希望只比较 BigDecimal 的 value，可以使用 compareTo 方法
        System.out.println(new BigDecimal("1.0").compareTo(new BigDecimal("1"))==0);

   /*     第一，切记，要精确表示浮点数应该使用 BigDecimal。并且，使用 BigDecimal 的
        Double 入参的构造方法同样存在精度丢失问题，应该使用 String 入参的构造方法或者
        BigDecimal.valueOf 方法来初始化。*/

        double a =3.35;
        double c=0.1;
        System.out.println();
        float b =3.35f;
        System.out.println(b);

    }
}

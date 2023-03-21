package com.wy.demo.java8.lambda.optional.省市区;

import cn.hutool.core.lang.Assert;
import lombok.Data;

import java.util.Optional;

@Data
public class TestCity {

    private Provice  provice;



    @Data
    public static class Provice {
        private City  city;
    }

    @Data
    public static class City {
      private Address address;
    }
    @Data
    public static class Address {
        private String name;
    }

    /**
     * 防止空指针的写法
     *
     * orElse:先执行
     *
     * orElseGet：后执行
     *
      * @param args
     */
    public static void main(String[] args) {
        //String name = new TestCity().provice.getCity().getAddress().getName();

        TestCity city1 = new TestCity();
        Optional<TestCity> city11 = Optional.ofNullable(city1);
        Address address = new Address();
        address.setName("luohe");
        City city = new City();
        city.setAddress(address);
        Provice provice = new Provice();
        provice.setCity(city);
        city1.setProvice(provice);
        String aa = city11.map(TestCity::getProvice).
                map(Provice::getCity).
                map(City::getAddress)
                .map(Address::getName).orElseGet(()-> {
                    return "未知";
                });
        System.out.println(aa);

    method2();
    }

    /**
     * 判空的另一种方式
     */
    private static void method2() {
        TestCity city1 = new TestCity();
        //为空的时候会报错
        Assert.notNull(city1,"主体不能为空");
        Provice provice = city1.getProvice();
        Assert.notNull(provice,"省份不能为空");



    }
}

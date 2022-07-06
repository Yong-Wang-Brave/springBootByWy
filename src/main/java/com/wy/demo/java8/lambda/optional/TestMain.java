package com.wy.demo.java8.lambda.optional;

import com.wy.demo.Exception.ServiceException;

import java.util.Optional;
import java.util.function.Supplier;

//https://mp.weixin.qq.com/s/5RbpFLpyFKzdQewg-aoEzg
public class TestMain {
    public static void main(String[] args) {
        Person person = new Person();
        Person person为null = Optional.ofNullable(person).orElse(new Person());
// 1、创建一个包装对象值为空的Optional对象
        Optional<String> optEmpty = Optional.empty();
// 2、创建包装对象值非空的Optional对象
        Optional<String> optOf = Optional.of("optional");
// 3、创建包装对象值允许为空也可以不为空的Optional对象
        Optional<String> optOfNullable1 = Optional.ofNullable(null);
        Optional<String> optOfNullable2 = Optional.ofNullable("optional");

        Person p = new Person();
        p.setAge(2);
        Person person1 = Optional.ofNullable(p).get();
        System.out.println(person1);

        if (Optional.ofNullable(person1).isPresent()) {
//写不为空的逻辑
            System.out.println("不为空");
        } else {
            //写为空的逻辑
            System.out.println("为空");
        }


        Person person11 = new Person();
        person11.setAge(2);
        Optional.ofNullable(person11).ifPresent(pp -> System.out.println("年龄" + pp.getAge()));
//过滤
        Optional.ofNullable(person11).filter(p1 -> p1.getAge() > 50);

        String optName = Optional.ofNullable(person11).map(p11 -> person.getName() + "22").orElse("name为空");
        System.out.println(optName);
//校验单个参数
        Optional<Object> optName1 = Optional.ofNullable(person11).map(p11 -> Optional.ofNullable(p11.getName()).orElse("name为空"));

        Optional.ofNullable(person11).orElse(new Person("小明", 2));

        Optional<Supplier<Person>> sup = Optional.ofNullable(Person::new);
        //调用get()方法，此时才会调用对象的构造方法，即获得到真正对象
        System.out.println(person);
        Person person2 = Optional.ofNullable(person).orElseGet(sup.get());

        System.out.println(person2);
        //person11=null;
        Optional.ofNullable(person11).orElseThrow(() -> new ServiceException("没有查询的相关数据"));

        String name为空 = Optional.ofNullable(person).map(rr -> rr.getName()).orElse("name为空");
        System.out.println(name为空);


    }
}

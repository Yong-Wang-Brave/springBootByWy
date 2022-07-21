package com.wy.demo.java8.lambda.flatmap;

import com.wy.demo.entity.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestFlatMap {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        List<User> list4 = new ArrayList<>();
        List<User> list5 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list2.add("aa");
        list2.add("bb");
        list2.add("cc");
        list3.add("a");
        list3.add("b");
        list3.add("c");
        list4.add(new User(1,"小红a",Arrays.asList(new User.Book("1","mulu1,mulu1"))));
        list4.add(new User(1,"小红aa",Arrays.asList(new User.Book("1","mulu1,mulu2"))));


        //1。将三个list合为一个
        final List<? extends Serializable> collect = Stream.of(list1, list2, list3).flatMap(s -> s.stream()).collect(Collectors.toList());
        System.out.println(collect.toString());
        //2.将两个List<Integer>合2为一
        List<String> list = Stream.of(list2, list3).flatMap(s -> s.stream()).collect(Collectors.toList());
        System.out.println(list.toString());
        //3.获取两个List<User>中得所有名字name集合
        List<String> collect1 = Stream.of(list4, list5).flatMap(s -> s.stream().map(User::getName)).collect(Collectors.toList());
        System.out.println(collect1.toString());



        //        打印所有书籍的名字。要求对重复的元素进行去重。


        list4.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .forEach(book -> System.out.println(book.getName()));



        //        打印现有数据的所有分类。要求对分类进行去重。不能出现这种格式：哲学,爱情     爱情

        list4.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .flatMap(book -> Arrays.stream(book.getCategory().split(",")))
                .distinct()
                .forEach(category-> System.out.println(category));



    }


}

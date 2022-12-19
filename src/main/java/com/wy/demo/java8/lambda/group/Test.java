package com.wy.demo.java8.lambda.group;

import com.google.common.collect.Lists;
 
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
 
import static java.util.stream.Collectors.groupingBy;
 
public class Test {
    public static void main(String[] args) {
        Employee employee1 = new Employee("松皇","总经理",1000000000);
        Employee employee2 = new Employee("小赵","助理",100000);
        Employee employee3 = new Employee("小智","打杂",20000);
        Employee employee4 = new Employee("小李","打杂",20001);
        ArrayList<Employee> employees = Lists.newArrayList(employee1, employee2, employee3, employee4);
        Map<String, Employee> collect =
                employees.stream().collect(groupingBy(Employee::getRank, Collectors.collectingAndThen(Collectors.reducing((c1,c2)->c1.getSalary()>c2.getSalary()?c1:c2), Optional::get)));
        System.out.println(collect);
    }



}
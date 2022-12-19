package com.wy.demo.java8.lambda.group;

import lombok.AllArgsConstructor;
import lombok.Data;
 
@Data
@AllArgsConstructor
public class Employee {
    private String name;
    private String rank;
    private Integer salary;
}
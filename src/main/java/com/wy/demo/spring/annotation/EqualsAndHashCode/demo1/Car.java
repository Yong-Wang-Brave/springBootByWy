package com.wy.demo.spring.annotation.EqualsAndHashCode.demo1;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 汽车
 */
@Data
public class Car {
 
    /**
     * 颜色
     */
    private String color;
 
    /**
     * 续航
     */
    private Integer endurance;
 
}
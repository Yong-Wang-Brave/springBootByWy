package com.wy.demo.spring.annotation.EqualsAndHashCode.demo1;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 比亚迪汽车
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BYD extends Car {
 
    /**
     * 价格
     */
    private Integer price;
 
}
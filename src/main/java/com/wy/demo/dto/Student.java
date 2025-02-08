package com.wy.demo.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Student {
    private BigDecimal number;
    private List<Student3> student3List;
}

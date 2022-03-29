package com.wy.demo.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Student {
    @NotEmpty(message = "姓名不可为空")
    String name;
    @NotEmpty(message = "年龄不可为空")
    String age;
}

package com.wy.demo.自定义注解.demo4;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAndTeacher {
    @Repalce(source = " ", target = "-")
    private String name;

}



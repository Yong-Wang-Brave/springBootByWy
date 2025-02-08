package com.wy.demo.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @NotEmpty(message = "姓名不可为空")
    String name;
    @NotEmpty(message = "年龄不可为空")
    String age;
    @NotEmpty(message = "卡号不可为空")
    String cardNo;

    String cardNo1;

    private BigDecimal number;

    private List<Student2>   student2List;
}

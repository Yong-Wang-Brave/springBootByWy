package com.wy.demo.自定义注解.demo3;

import com.wy.demo.mybatiesInterceptor.FillType;
import lombok.Data;


@Data
public class CustomerDTO {
    private String name;
    private String age;
    @Encrypt(original = "mobileNo",columnName = "phone",tableName = "customer", fillType = FillType.ENCRYPT)
    private String mobileNo;
}

package com.wy.demo.亮点.自定义注解实现统一返回;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ResultCode {
    SUCESS(1,"成功"),
    ERROR(2,"失败");
    private Integer code;
    private String message;

}

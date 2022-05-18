package com.wy.demo.lightspot.UnitedReturn;

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

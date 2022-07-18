package com.wy.demo.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    private String code;
    private String msg;
    public static BaseResponse success() {
        return new BaseResponse("0", "成功");
    }
    public static BaseResponse fail() {
        return new BaseResponse("1", "失败");
    }
    //构造器、getter、setter方法
}
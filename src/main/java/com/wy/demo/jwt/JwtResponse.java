package com.wy.demo.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse extends BaseResponse {
    private String code;
    private String msg;
    private String jwtData;
    public static JwtResponse success(String jwtData) {
        BaseResponse success = BaseResponse.success();
        return new JwtResponse(success.getCode(), success.getMsg(), jwtData);
    }
    public static JwtResponse fail(String jwtData) {
        BaseResponse fail = BaseResponse.fail();
        return new JwtResponse(fail.getCode(), fail.getMsg(), jwtData);
    }
    //构造器、getter、setter方法
}
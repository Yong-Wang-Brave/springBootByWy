package com.wy.demo.lightPoint.tokenGetUserInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCenterResult {

    private String code;

    private String msg;

    private UserCenterUserInfo data;
}

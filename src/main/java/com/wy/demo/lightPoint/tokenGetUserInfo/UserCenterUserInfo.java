package com.wy.demo.lightPoint.tokenGetUserInfo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCenterUserInfo {

    private String userId;

    private String phone;

    //避免返回结果为null的时候解析出现空指针
    private Userportal userportal =new Userportal();
    public void setUserportal(Userportal userportal){
        if (userportal==null) {
            userportal=new Userportal();
        }
        this.userportal=userportal;
    }
    @Data
    public static class Userportal{
        private String unionId;
    }
}

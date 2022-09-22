package com.wy.demo.lightPoint.tokenGetUserInfo.threadLocal;

public class UserContext {
    private UserContext(){}

    private static final   ThreadLocal<UserInfo>  LOCAL =new ThreadLocal<>();
    public static void setUserInfo(UserInfo userInfo){LOCAL.set(userInfo);}
    public static  UserInfo  getUserInfo(){
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }

    public static class UserInfo{
        public String getUserId() {
            return userId;
        }

        public UserInfo setUserId(String userId) {
            this.userId = userId;

            return this;
        }

        private String userId;

        public String getNickname() {
            return nickname;
        }

        public UserInfo setNickname(String nickname) {
            this.nickname = nickname;
            return  this;
        }

        private String nickname;
    }
}

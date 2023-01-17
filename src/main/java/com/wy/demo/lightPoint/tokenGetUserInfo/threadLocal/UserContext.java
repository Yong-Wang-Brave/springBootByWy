package com.wy.demo.lightPoint.tokenGetUserInfo.threadLocal;

public class UserContext {
    private UserContext() {
    }

    private static final ThreadLocal<UserInfo> LOCAL = new ThreadLocal<>();

    public static void setUserInfo(UserInfo userInfo) {
        LOCAL.set(userInfo);
    }

    public static UserInfo getUserInfo() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }

    public static class UserInfo {

        private String userId;


        private String nickname;

    /*    public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }*/
 public String getUserId() {
            return userId;
        }

        public UserInfo setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public String getNickname() {
            return nickname;
        }
        public UserInfo setNickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

    }
}

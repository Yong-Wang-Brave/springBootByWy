package com.wy.demo.mybatis.typehandler;

/**
 * @author huan.fu 2021/5/18 - 下午4:37
 */
public class Encrypt {
    private String value;

    public Encrypt() {
    }

    public Encrypt(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

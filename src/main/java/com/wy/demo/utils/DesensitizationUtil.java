package com.wy.demo.utils;

import org.apache.commons.lang.StringUtils;

/**
 * 只显示第一个汉字，其他隐藏为2个星号(例子：王**）
 * 脱敏工具类
 */
public class DesensitizationUtil {

    public static String left(String fullName, int index) {
        if (StringUtils.isBlank(fullName)) {
            return "";
        }
        String name = StringUtils.left(fullName, index);
        return StringUtils.rightPad(name, StringUtils.length(fullName), "*");
    }

    /**
     * 110***57 前三位明文，后面保留2位明文
     *
     * @param name
     * @param index
     * @param end
     * @return
     */
    public static String around(String name, int index, int end) {
        if (StringUtils.isBlank(name)) {
            return "";
        }
        return StringUtils.left(name, index).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(name, end), StringUtils.length(name), "*"), "***"));
    }

    /**
     * 显示后4位 其他隐藏
     *
     * @param num
     * @param end
     * @return
     */
    public static String right(String num, int end) {

        if (StringUtils.isBlank(num)) {
            return "";
        }
        return StringUtils.leftPad(StringUtils.right(num, end), StringUtils.length(num), "*");
    }

    /**
     * 手机号码前三后四脱敏
     *
     * @param mobile
     * @return
     */
    public static String mobileEncrypt(String mobile) {

        if (StringUtils.isEmpty(mobile) || mobile.length() != 11) {
            return mobile;
        }
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    //身份证前三后四脱敏
    public static String idPassport(String id) {
        if (StringUtils.isEmpty(id) || id.length() < 8) {
            return id;
        }
        return id.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*");
    }

    /**
     * 护照前2后3位脱敏，护照一般8到9位
     *
     * @param id
     * @return
     */
    public static String idPassPort(String id) {
        if (StringUtils.isEmpty(id) || id.length() < 8) {
            return id;
        }
        return id.substring(0, 2) + new String(new char[id.length() - 5]).replace("\0", "*") + id.substring(id.length() - 3);
    }


    /**
     * 证件后几位脱敏
      * @param id
     * @param sensitiveSize
     * @return
     */
    public static String idPassport(String id, int sensitiveSize) {
        if (StringUtils.isBlank(id)) {
            return "";
        }
        int length = StringUtils.length(id);
        return StringUtils.rightPad(StringUtils.left(id, length - sensitiveSize), length, "*");
    }


    public static void main(String[] args) {
        String left = left("王大胆",1);
        System.out.println(left);

        String around = around("一二三四五六七八九", 2, 1);
        System.out.println(around);

        String right = right("一二三四五六", 2);
        System.out.println(right);
        String s = mobileEncrypt("13409871200");
        System.out.println(s);

        String s1 = idPassport("422231299203240097");
        System.out.println(s1);
        String s2 = idPassport("123456789",2);
        System.out.println(s2);

    }

}
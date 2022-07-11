package com.wy.demo.shejimoshi.策略模式20220708;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

@Getter
@NoArgsConstructor
public enum MsgTypeEnum {

    TEXT(1, "文本"),
    IMAGE(2, "图片"),
    VIDEO(3, "视频");

    public  int code;
    public  String name;

    MsgTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
    public static MsgTypeEnum getByCode(int code){
        for (MsgTypeEnum value : MsgTypeEnum.values()) {
            if (value.getCode()==code) {
                return value;
            }
        }
        return null;
    }

    public static String getDesc(int code){
        for (MsgTypeEnum value : MsgTypeEnum.values()) {
            if (value.getCode()==code) {
                return value.getName();
            }
        }
        return StringUtils.EMPTY;
    }
}
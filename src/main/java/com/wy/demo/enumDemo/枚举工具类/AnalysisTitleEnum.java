package com.wy.demo.enumDemo.枚举工具类;

import lombok.Getter;

@Getter
public enum AnalysisTitleEnum {
    SPORT("sport","健康运动"),
    SMOKING("SMOKE","吸烟");

    private String code;
    private String desc;

    AnalysisTitleEnum(String code,String desc){
        this.code = code;
        this.desc = desc ;
    }

    public static String getDescByCode(String code){
        for (AnalysisTitleEnum value : AnalysisTitleEnum.values()) {
            if (value.getCode().equals(code)) {
                return value.getDesc();
            }
        }
        return "";
    }
}

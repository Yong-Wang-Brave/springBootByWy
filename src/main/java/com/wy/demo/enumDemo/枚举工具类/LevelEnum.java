package com.wy.demo.enumDemo.枚举工具类;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum LevelEnum {
//这个要放在前面
 FIRST("FIRST","一级"),
SECOND("SECOND","二级"),
THIRD("THIRD","三级");
    private String code;
    private String value;
    public static LevelEnum getBeanByCode(String code){
        LevelEnum[] values = LevelEnum.values();
        for (LevelEnum value : values) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}

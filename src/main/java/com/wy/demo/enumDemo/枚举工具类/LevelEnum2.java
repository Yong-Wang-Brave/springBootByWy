package com.wy.demo.enumDemo.枚举工具类;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
//统一优化枚举
public enum LevelEnum2 implements EnumAbility{
//这个要放在前面
 FIRST("FIRST","一级"),
SECOND("SECOND","二级"),
THIRD("THIRD","三级");
    private String code;
    private String value;
    public static LevelEnum2 getBeanByCode(String code){
        return (LevelEnum2) EnumAbilityUtil.getEnumByCode(LevelEnum2.class, code);
    }

    @Override
    public String getDescription() {
        return code;
    }
}

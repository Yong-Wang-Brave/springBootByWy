package com.wy.demo.enumDemo.枚举工具类;

import java.math.BigDecimal;

public enum ManageResultConfigEnum {
    ZERO(new BigDecimal("-1"),new BigDecimal("0"),"0打卡星人","嘿，0打卡星人"),
    SEEDS(new BigDecimal("0"),new BigDecimal("50"),"嗮网种子选手","嘿，嗮网种子选手"),
    ALMIGHTYACE(new BigDecimal("90"),new BigDecimal("100"),"全能王牌","嘿，全能王牌"),
    SUPERMVP(new BigDecimal("100"),new BigDecimal("100"),"超凡MVP","嘿，超凡MVP");
    private BigDecimal start;
    private BigDecimal end;
    private  String result;
    private String resultDesc;

    ManageResultConfigEnum(BigDecimal start, BigDecimal end, String result, String resultDesc) {
        this.start = start;
        this.end = end;
        this.result = result;
        this.resultDesc = resultDesc;
    }

    /**
     * -1  第一个数小  0   相等  1  第一个数大
     * @param persent
     * @return
     */
    public static ManageResultConfigEnum byPercent(BigDecimal persent){
        if (new BigDecimal("0").compareTo(persent)==0) {
            return ZERO;
        }
        if (new BigDecimal("100").compareTo(persent)==-1 || new BigDecimal("100").compareTo(persent)==0  ) {
            return SUPERMVP;
        }

        for (ManageResultConfigEnum value : values()) {
            if (value.start.compareTo(persent) == -1 && (value.end.compareTo(persent) ==1 || value.end.compareTo(persent) == 0 )) {
                return  value;
            }
        }
        return  null;
    }
}

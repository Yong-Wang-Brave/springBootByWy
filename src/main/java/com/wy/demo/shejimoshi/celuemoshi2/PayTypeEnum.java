package com.wy.demo.shejimoshi.celuemoshi2;

import lombok.Getter;

/**
 * 支付类型枚举
 * @Author WDYin
 * @Date 2022/4/16
 **/
@Getter
public enum PayTypeEnum {

    BALANCE("1","账户余额支付"),
    YU_E_BAO("2","余额宝支付"),
    HUA_BEI("3","花呗支付"),
    BANK("4","银行卡支付");

    private String code;
    private String value;

    PayTypeEnum(String code,String value) {
        this.code = code;
        this.value = value;
    }
}

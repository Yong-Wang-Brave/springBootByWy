package com.wy.demo.shejimoshi.策略模式22;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ToolEnum {

    TOOL_CAR("car","汽车"),
    TOOL_BIKE("bike","自行车");

    private String code;
    private String desc;


    public static  String getDesc(String  code){
        for (ToolEnum value : ToolEnum.values()) {
            if (value.getCode().equals(code)) {
                return value.getDesc();
            }
        }
        return "";
    }

}

package com.wy.demo.xielaoshi.xianchengchigongjulei;

public enum ThreadPoolExecuteTypeEnum {
    SYN("同步提交"),
    ASY("异步提交");
    private String value;

    private ThreadPoolExecuteTypeEnum(String value) {
        this.value = value;
    }
}

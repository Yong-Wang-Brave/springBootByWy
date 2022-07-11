package com.wy.demo.shejimoshi.策略模式20220708;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageInfo {

    // 消息类型
    private MsgTypeEnum type;
    // 消息内容
    private String content;

}
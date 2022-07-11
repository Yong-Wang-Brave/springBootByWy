package com.wy.demo.shejimoshi.策略模式20220708;

import org.springframework.stereotype.Service;

@Service
@MsgTypeHandler(value = MsgTypeEnum.TEXT)
public class TextMessageStrategy implements MessageStrategy {

    @Override
    public void handleMessage(MessageInfo messageInfo) {
        System.out.println("处理文本消息 " + messageInfo.getContent());
    }
}
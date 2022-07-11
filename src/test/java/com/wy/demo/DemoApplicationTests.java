package com.wy.demo;

import com.wy.demo.shejimoshi.策略模式20220708.MessageInfo;
import com.wy.demo.shejimoshi.策略模式20220708.MessageStrategy;
import com.wy.demo.shejimoshi.策略模式20220708.MsgTypeEnum;
import com.wy.demo.shejimoshi.策略模式20220708.MsgTypeHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    private Map<MsgTypeEnum, MessageStrategy> messageStrategyMap;

    @Resource
    private void setMessageStrategyMap(List<MessageStrategy> messageStrategies) {
        System.out.println(messageStrategies.stream().collect(Collectors.toList()));
        messageStrategyMap = messageStrategies.stream().filter(v->AnnotationUtils.findAnnotation(v.getClass(), MsgTypeHandler.class)!=null).collect(
                Collectors.toMap(item -> AnnotationUtils.findAnnotation(item.getClass(), MsgTypeHandler.class).value(), v -> v));
    }

    @Test
    public void contextLoads() {
        int type =2;
        MessageInfo messageInfo = new MessageInfo(MsgTypeEnum.getByCode(type), "这是一个文本消息");
        MessageStrategy messageStrategy = messageStrategyMap.get(messageInfo.getType());
        // 处理文本消息 这是一个文本消息
        messageStrategy.handleMessage(messageInfo);
    }
}
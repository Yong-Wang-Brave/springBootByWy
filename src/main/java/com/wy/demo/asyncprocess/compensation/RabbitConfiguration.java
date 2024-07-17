package com.wy.demo.asyncprocess.compensation;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 生产者将消息发送到交换器，交换器根据绑定规则将消息路由到相应的队列。消费者可以订阅队列并从中接收消息进行处理。
 */
//@Configuration
public class RabbitConfiguration {

    public static final String QUEUE = "newuserQueueCompensation";
    public static final String EXCHANGE = "newuserExchangeCompensation";
    public static final String ROUTING_KEY = "newuserRoutingCompensation";

    //队列
    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }

    //交换器
    @Bean
    public Exchange exchange() {
        return ExchangeBuilder.directExchange(EXCHANGE).durable(true).build();
    }

    //绑定
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(ROUTING_KEY).noargs();
    }
}

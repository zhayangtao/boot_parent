package com.example.boot_rabbitmq.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/2/8
 * rabbitmq 消息发送者
 */
@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send() {
        System.out.println("sended");
        String content = "Hello World! today is " + new Date();
    }

    public void sendTopic1() {
        System.out.println("sended1");
        String content = "Hello World! message1";
        this.amqpTemplate.convertAndSend("boot_exchange", "topic.message",  content);
    }

    public void sendTopic2() {
        System.out.println("sended2");
        String content = "Hello World! message2";
        this.amqpTemplate.convertAndSend("boot_exchange", "topic.messages",  content);
    }

    public void sendFanout() {
        System.out.println("sendFanout");
        String content = "Hello World! sendFanout";
        // fanout模式下第二个参数将被忽略
        this.amqpTemplate.convertAndSend("boot_fanoutExchange", "",  content);
    }
}

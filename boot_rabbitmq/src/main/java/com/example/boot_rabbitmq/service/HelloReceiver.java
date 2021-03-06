package com.example.boot_rabbitmq.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/2/8
 */
@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {

    @RabbitHandler
    public void receive(String hello) {
        System.out.println("receive " + hello);
    }
}

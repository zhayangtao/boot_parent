package com.example.boot_rabbitmq.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/03
 */
@Service(value = "testMessageListener")
public class TestMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("testMessageListener onMessage");
    }
}

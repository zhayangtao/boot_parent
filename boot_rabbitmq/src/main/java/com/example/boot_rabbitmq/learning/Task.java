package com.example.boot_rabbitmq.learning;


import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/01
 */
public class Task {
    private final static String QUEUE_NAME = "workqueue-durable";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(AMQP.PROTOCOL.PORT);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        boolean durable = true; // 设置消息持久化
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
        for (int i = 5; i > 0; i++) {
            StringBuilder dots = new StringBuilder();
            for (int j = 0; j <= i; j++) {
                dots.append(".");
            }
            String message = "helloworld" + dots + dots.length();
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            System.out.println("Sent Message: " + message);
        }
        // 关闭通道
        channel.close();
        connection.close();
    }
}

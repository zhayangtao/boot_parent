package com.example.boot_rabbitmq.learning;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/01
 */
public class SendLog {
    private static final String EXCHANGE_NAME = "ex_log";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(AMQP.PROTOCOL.PORT);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String message = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ": log";
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println("Sent " + message);
        channel.close();
        connection.close();
    }
}

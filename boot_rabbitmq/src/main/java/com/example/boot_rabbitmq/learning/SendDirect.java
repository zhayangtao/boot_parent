package com.example.boot_rabbitmq.learning;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/01
 */
public class SendDirect {
    private static final String EXCHANGE_NAME = "ex_logs_direct";
    private static final String[] SEVERITIES = {"info", "warning", "error"};

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(AMQP.PROTOCOL.PORT);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        for (int i = 0; i < 6; i++) {
            String severity = getSeverity();
            String message = severity + "_log:" + UUID.randomUUID().toString();
            channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
            System.out.println("sent " + message);
        }
        channel.close();
        connection.close();
    }

    private static String getSeverity() {
        Random random = new Random();
        int ranVal = random.nextInt(3);
        return SEVERITIES[ranVal];
    }
}

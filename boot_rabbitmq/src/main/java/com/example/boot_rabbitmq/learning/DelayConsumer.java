package com.example.boot_rabbitmq.learning;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/03
 */
public class DelayConsumer {
    private static final String QUEUE_NAME = "message_ttl_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(AMQP.PROTOCOL.PORT);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", "amq.direct");
        arguments.put("x-dead-letter-routing-key", "message_ttl_routingKey");
        channel.queueDeclare("delay_queue", true, false, true, arguments);
        channel.queueDeclare(QUEUE_NAME, true, false, true, null);
        channel.queueBind(QUEUE_NAME, "amq.direct", "message_ttl_routingKey");

        channel.basicConsume(QUEUE_NAME, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                System.out.println("received message:" + new String(body) + ",date: " + System.currentTimeMillis());
            }
        });
    }
}

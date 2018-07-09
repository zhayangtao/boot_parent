package com.example.boot_rabbitmq.learning;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/01
 */
public class Work {
    private static final String QUEUE_NAME = "workqueue-durable";

    public static void main(String[] args) throws IOException, TimeoutException {
        int hashCode = Work.class.hashCode();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(AMQP.PROTOCOL.PORT);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        boolean durable = true;
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
        channel.basicConsume(QUEUE_NAME, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String routingKey = envelope.getRoutingKey();
                String contentType = properties.getContentType();
                long deliveryTag = envelope.getDeliveryTag();
                channel.basicAck(deliveryTag, false);
            }
        });
    }
}

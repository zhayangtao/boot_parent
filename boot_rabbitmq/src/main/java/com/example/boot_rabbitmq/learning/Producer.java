package com.example.boot_rabbitmq.learning;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.ExchangeTypes;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/02
 */
public class Producer {

    private static final String EXCHANGE_NAME = "header-exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(AMQP.PROTOCOL.PORT);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 声明转发器和类型 headers
        channel.exchangeDeclare(EXCHANGE_NAME, ExchangeTypes.HEADERS, false, true, null);
        String message = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + " : log something";
        Map<String, Object> headers = new Hashtable<>();
        headers.put("aaa", "01234");
        AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties.Builder();
        properties.headers(headers);

        channel.basicPublish(EXCHANGE_NAME, "", properties.build(), message.getBytes());
        channel.close();
        connection.close();
    }
}

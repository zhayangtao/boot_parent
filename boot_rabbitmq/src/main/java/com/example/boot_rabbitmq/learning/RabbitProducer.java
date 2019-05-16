package com.example.boot_rabbitmq.learning;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.function.BiFunction;
import java.util.function.IntUnaryOperator;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/19
 */
public class RabbitProducer {
    private static final String EXCHANGE_NAME = "exchange_demo";
    private static final String ROUTING_KEY = "routingkey_demo";
    private static final String QUEUE_NAME = "queue_demo";
    private static final String IP_ADDRESS = "192.168.0.160";
    private static final int PORT = 5672; // 默认端口
    private static final String  USERNAME = "root"; // 默认端口
    private static final String PASSWORD = "1234";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);

        Connection connection = factory.newConnection(); // 创建链接
        Channel channel = connection.createChannel(); // 创建信道
        // 创建一个 type = direct 持久化的、非自动删除的交换器
        channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);
        // 创建一个持久化、非排他的、非自动删除的队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        // 将交换器与队列通过路由键绑定
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
        String message = "HelloWorld";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        // 关闭
        channel.close();
        connection.close();
    }

    public void test() {
        IntUnaryOperator staticMethod = RabbitProducer::staticMethod;
        staticMethod.applyAsInt(111);

        IntUnaryOperator normalMethod = this::normalMethod;
        normalMethod.applyAsInt(111);

        this.normalMethod2(1);

        BiFunction<RabbitProducer, Integer, Integer> normalMethod2 = RabbitProducer::normalMethod2;
        normalMethod2.apply(this, 111);
    }

    private static int staticMethod(int i) {
        return i * 2;
    }

    private int normalMethod(int i) {
        System.out.println("实例方法");
        return i * 3;
    }


    private int normalMethod2(RabbitProducer this, int i) {
        return i * 2;
    }
}

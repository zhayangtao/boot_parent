package com.example.boot_rabbitmq.learning;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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

    private static ConnectionFactory factory = new ConnectionFactory();
    static {
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
    }

    public static void main(String[] args) throws IOException, TimeoutException {
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

    public void test1() throws IOException, TimeoutException {
        Map<String, Object> args = new HashMap<>();
        args.put("alternate-exchange", "myAe");

        Connection connection = factory.newConnection(); // 创建链接
        Channel channel = connection.createChannel(); // 创建信道

        channel.exchangeDeclare("normalExchange", BuiltinExchangeType.DIRECT, true, false, args);
        channel.exchangeDeclare("myAe", "fanout", true, false, null);
        channel.queueDeclare("normalQueue", true, false, false, null);
        channel.queueBind("normalQueue", "normalExchange", "normalKey");
        channel.queueDeclare("unrouteQueue", true, false, false, null);
        channel.queueBind("unrouteQueue", "myAe", "");
    }

    /**
     * 测试死信队列
     */
    public void testDlx() throws IOException, TimeoutException {
        Connection connection = factory.newConnection(); // 创建链接
        Channel channel = connection.createChannel(); // 创建信道
        channel.exchangeDeclare("exchange.dlx", "direct", true);
        channel.exchangeDeclare("exchange.normal", "fanout", true);

        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 10000);
        args.put("x-dead-letter-exchange", "exchange.dlx");
        args.put("x-dead-letter-routine-key", "routingkey");

        channel.queueDeclare("queue.normal", true, false, false, args);
        channel.queueBind("queue.normal", "exchange.normal", "");
        channel.queueDeclare("queue.dlx", true, false, false, null);
        channel.queueBind("queue.dlx", "exchange.dlx", "routingkey");
        channel.basicPublish("exchange.normal", "rk", MessageProperties.PERSISTENT_TEXT_PLAIN, "dlx".getBytes());

    }
}

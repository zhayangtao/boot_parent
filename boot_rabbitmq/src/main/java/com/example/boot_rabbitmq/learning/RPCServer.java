package com.example.boot_rabbitmq.learning;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.concurrent.TimeoutException;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/03
 */
public class RPCServer {
    private static final String RPC_QUEUE_NAME = "rpc_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(AMQP.PROTOCOL.PORT);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RPC_QUEUE_NAME, false, false, true, null);
        // 通过channel.basicQos设置prefetchCount属性可将负载平均分配到多台服务器上
        channel.basicQos(1);
        channel.basicConsume(RPC_QUEUE_NAME, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.out.println("[.]getMd5String(" + message + ")");
                String response = getMd5String(message);
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties()
                        .builder().correlationId(properties.getCorrelationId()).build();
                channel.basicPublish("", properties.getReplyTo(), replyProps, response.getBytes());
                // 发送应答
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }

    /// 模拟RPC方法 获取MD5字符串
    private static String getMd5String(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }
}

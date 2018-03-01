package com.example.boot_rabbitmq.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/2/8
 */
@Configuration
public class TopicRabbitConfig {

    final static String message = "topic.message";
    final static String messages = "topic.messages";

    @Bean
    public Queue queueMessage() {
        return new Queue(TopicRabbitConfig.message);
    }

    @Bean
    public Queue queueMessages() {
        return new Queue(TopicRabbitConfig.messages);
    }

    // 定义交换机
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("boot_exchange");
    }

    // 绑定队列和交换机
    @Bean
    public Binding bindingExchangeMessage(Queue queueMessage, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueMessage).to(topicExchange).with("topic.message");
    }

    @Bean
    public Binding bindingExchangeMessages(Queue queueMessages, TopicExchange topicExchange) {
        // # 匹配一个或多个单词  * 匹配一个单词
        return BindingBuilder.bind(queueMessages).to(topicExchange).with("topic.#");
    }


}

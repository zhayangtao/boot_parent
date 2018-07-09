package com.example.boot_rabbitmq.learning;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/03
 */
@Configuration
public class AnnotationConfiguration {
    private String springQueueDemo = "spring-queue-demo";

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory();
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setRoutingKey(this.springQueueDemo);
        template.setQueue(this.springQueueDemo);
        return template;
    }

    public Queue helloWorldQueue() {
        return new Queue(this.springQueueDemo);
    }
}

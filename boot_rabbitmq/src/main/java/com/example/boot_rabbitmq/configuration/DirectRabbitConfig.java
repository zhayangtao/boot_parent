package com.example.boot_rabbitmq.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/2/8
 */
@Configuration
public class DirectRabbitConfig {

    @Bean
    public Queue queue() {
        return new Queue("hello");
    }
}

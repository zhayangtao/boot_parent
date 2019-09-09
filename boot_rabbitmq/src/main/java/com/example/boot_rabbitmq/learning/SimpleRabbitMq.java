package com.example.boot_rabbitmq.learning;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author no one
 * @version 1.0
 * @since 2019/09/03
 */
@Component
public class SimpleRabbitMq {

    @Bean
    Queue queue() {
        return new Queue("", false, false, true);
    }
}

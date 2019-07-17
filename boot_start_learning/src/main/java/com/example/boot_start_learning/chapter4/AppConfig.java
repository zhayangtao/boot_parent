package com.example.boot_start_learning.chapter4;

import com.example.boot_start_learning.chapter2.MessageProvider;
import com.example.boot_start_learning.chapter2.MessageRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/12
 */
@Configuration
public class AppConfig {
    @Bean
    public MessageProvider messageProvider() {
        return new ConfigurableMessageProvider();
    }

    @Bean
    public MessageRenderer messageRenderer() {
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(messageProvider());
        return renderer;
    }
}

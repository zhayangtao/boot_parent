package com.example.boot_start_learning.chapter4;

import com.example.boot_start_learning.chapter2.MessageProvider;
import com.example.boot_start_learning.chapter2.MessageRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/12
 */
@Configuration
@PropertySource(value = "classpath:message.properties")
public class AppConfigOne {
    @Autowired
    private Environment environment;

    @Bean
    public MessageProvider messageProvider() {
        return new ConfigurableMessageProvider(environment.getProperty("message"));
    }

    @Bean
    public MessageRenderer renderer() {
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(messageProvider());
        return renderer;
    }
}

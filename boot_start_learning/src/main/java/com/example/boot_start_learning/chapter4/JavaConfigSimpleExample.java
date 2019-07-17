package com.example.boot_start_learning.chapter4;

import com.example.boot_start_learning.chapter2.MessageProvider;
import com.example.boot_start_learning.chapter2.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/12
 */
public class JavaConfigSimpleExample {
    @Configuration
    static class AppConfigOne {
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

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfigOne.class);
        MessageRenderer renderer = context.getBean("messageRenderer", MessageRenderer.class);

    }
}

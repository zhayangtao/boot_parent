package com.example.boot_start_learning.chapter4;

import com.example.boot_start_learning.chapter2.MessageProvider;
import com.example.boot_start_learning.chapter2.MessageRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/12
 */
@Configuration
@Import(AppConfigFour.class)
public class AppConfigThree {

    @Autowired
    private MessageProvider provider;

    @Bean
    public MessageRenderer messageRenderer() {
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(provider);
        return renderer;
    }
}

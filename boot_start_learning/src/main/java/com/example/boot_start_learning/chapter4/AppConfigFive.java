package com.example.boot_start_learning.chapter4;

import com.example.boot_start_learning.chapter2.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/12
 */
@Configuration
@ImportResource(value = "classpath:app-context-01.xml")
public class AppConfigFive {
    @Autowired
    private MessageProvider provider;



}

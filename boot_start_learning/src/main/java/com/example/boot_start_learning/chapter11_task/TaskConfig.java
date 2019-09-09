package com.example.boot_start_learning.chapter11_task;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/30
 */
@Configuration
//@ImportResource("classpath:task-namespace-app-context.xml")
@EnableScheduling
@EnableAsync
@Import(DataServiceConfig.class)
public class TaskConfig {
}

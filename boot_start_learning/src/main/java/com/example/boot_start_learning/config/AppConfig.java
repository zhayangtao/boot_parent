package com.example.boot_start_learning.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/24
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.example.boot_start_learning.chapter7_hibernate")
@ImportResource(value = "classpath:hibernate.xml")
public class AppConfig {

}

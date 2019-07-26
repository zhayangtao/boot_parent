package com.example.boot_start_learning.chapter7_hibernate.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/25
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.example.boot_start_learning.chapter7_hibernate.service")
@ImportResource(locations = "classpath:jpa.xml")
public class JpaConfig {
//    private static Logger logger = LoggerFactory.getLogger(JpaConfig.class);

}

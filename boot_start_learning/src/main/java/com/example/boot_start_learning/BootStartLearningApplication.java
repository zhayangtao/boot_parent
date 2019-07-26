package com.example.boot_start_learning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class BootStartLearningApplication {
    private static Logger logger = LoggerFactory.getLogger(BootStartLearningApplication.class);

    public static void main(String[] args) {
        /*ConfigurableApplicationContext context = SpringApplication.run(BootStartLearningApplication.class);
        assert context != null;
        logger.info("The beans you were looking for:");
        Arrays.stream(context.getBeanDefinitionNames()).forEach(logger::info);*/
        SpringApplication.run(BootStartLearningApplication.class, args);
    }

}

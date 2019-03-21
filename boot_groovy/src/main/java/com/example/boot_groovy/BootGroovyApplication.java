package com.example.boot_groovy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class BootGroovyApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootGroovyApplication.class, args);
    }

}


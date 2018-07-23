package com.example.boot_rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // 支持定时任务
public class BootRabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootRabbitmqApplication.class, args);
	}
}

package com.example.boot_13_redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling // 支持定时任务
@EnableAsync // 支持异步调用
public class Boot13RedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot13RedisApplication.class, args);
	}
}

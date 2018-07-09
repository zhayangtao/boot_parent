package com.example.boot.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2017/12/28
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public KeyGenerator keyGenerator() {
        return (Object target, Method method, Object... objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : objects) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }
}

package com.example.boot_webflux.repository;

import com.example.boot_webflux.entity.MyEvent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/26
 */
public interface MyEventRepository extends ReactiveMongoRepository<MyEvent, Long> {
    @Tailable // 被注解的方法将发送无限流，需要注解在返回值在 Flux 这样的多个元素的 Publisher 方法上
    Flux<MyEvent> findBy();
}

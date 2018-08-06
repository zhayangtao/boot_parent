package com.example.boot_mybatis_plus.config;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/25
 */
@Component
class UserHandler {

    public Mono<ServerResponse> getUser(ServerRequest request) {
        return Mono.empty();
    }

    public Mono<ServerResponse> getUserCustomers(ServerRequest request) {
        return Mono.empty();
    }

    public Mono<ServerResponse> deleteUser(ServerRequest request) {
        return Mono.empty();
    }
}
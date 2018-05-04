package com.example.boot_webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/25
 */
@RestController
public class HelloFluxController {

    @GetMapping("/helloFlux")
    public Mono<String> hello() {
        return Mono.just("hello Flux");
    }

    @GetMapping("/helloFlux/{latency}")
    public Mono<String> hello(@PathVariable int latency) {
        return Mono.just("Welcome to reactive world")
                .delayElement(Duration.ofMillis(latency));
    }
}

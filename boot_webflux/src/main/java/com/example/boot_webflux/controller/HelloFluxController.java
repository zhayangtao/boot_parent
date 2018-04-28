package com.example.boot_webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

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
}

package com.example.boot_webflux.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

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

    public void springFlux() {
        HandlerFunction<ServerResponse> timeFunction = serverRequest -> ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(Mono.just("now is " + new SimpleDateFormat("HH:mm:ss").format(new Date())), String.class);
        RouterFunction<ServerResponse> router = RouterFunctions.route(GET("/time"), timeFunction);

    }
}

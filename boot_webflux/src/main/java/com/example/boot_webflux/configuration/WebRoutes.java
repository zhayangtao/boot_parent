package com.example.boot_webflux.configuration;

import com.example.boot_webflux.handler.TimeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/25
 */
@Configuration
public class WebRoutes {

    @Autowired
    private TimeHandler timeHandler;

    @Bean
    public RouterFunction<ServerResponse> route1() {
        return route(
                GET("/"), serverRequest -> {
                    Mono<String> mono = Mono.just("hello world");
                    return ok().body(BodyInserters.fromPublisher(mono, String.class));
                }
        );
    }

    @Bean
    public RouterFunction<ServerResponse> timerRouter() {
        return route(GET("/time"), req -> timeHandler.getTime(req))
                .andRoute(GET("/date"), timeHandler::getDate)
                .andRoute(GET("/times"), timeHandler::sendTimePerSec);  // 这种方式相对于上一行更加简洁
    }
}

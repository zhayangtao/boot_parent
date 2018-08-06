package com.example.boot_mybatis_plus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/25
 * 基于功能的路由
 */
@Configuration
public class RoutingConfiguration {

    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction(UserHandler userHandler) {
        return route(GET("/{user}").and(accept(MediaType.APPLICATION_JSON)), userHandler::getUser)
                .andRoute(GET("/{user}/customers").and(accept(MediaType.APPLICATION_JSON)), userHandler::getUserCustomers)
                .andRoute(DELETE("/{user}").and(accept(MediaType.APPLICATION_JSON)), userHandler::deleteUser);
    }
}



package com.example.boot_mybatis_plus.controller;

import com.example.boot_mybatis_plus.entity.Customer;
import com.example.boot_mybatis_plus.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/25
 */
@RestController
@RequestMapping("/users")
public class MyRestController {
    @GetMapping("/{user}")
    public Mono<User> getUser(@PathVariable Long user) {
        return Mono.empty();
    }

    @GetMapping("/{user}/customers")
    public Flux<Customer> getUserCustomers(@PathVariable Long user) {
        return Flux.empty();
    }
}

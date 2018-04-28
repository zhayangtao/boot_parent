package com.example.boot_webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/25
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Welcome";
    }
}

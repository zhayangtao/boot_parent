package com.example.boot_webflux.controller;

import com.example.boot_webflux.entity.MyEvent;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

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

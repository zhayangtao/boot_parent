package com.example.boot_17.controller;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/13
 */
@RestController
public class LimiterController {

    private RateLimiter rateLimiter = RateLimiter.create(10);

    @GetMapping("/buy")
    public String test(int count, String code) {
        System.out.println(count);
        System.out.println(code);
        if (rateLimiter.tryAcquire(1000, TimeUnit.MILLISECONDS)) {
            return "success";
        }
        return "fail";

    }
}
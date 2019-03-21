package com.example;

import act.controller.annotation.UrlContext;
import org.osgl.mvc.annotation.GetAction;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/12/26
 */
@UrlContext("/home")
public class HomeController {
    @GetAction("greeting")
    public String greeting() {
        return "hello world";
    }
}

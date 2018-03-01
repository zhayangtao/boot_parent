package com.example.jfinaldemo.controller;

import com.jfinal.core.Controller;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/2/22
 */

public class HelloController extends Controller {
    /**
     * 在 Controller 之中定义的 public 方法称为 Action。Action 是请求的最小单位。Action 方法
     * 必须在 Controller 中定义，且必须是 public 可见性。
     */
    public void index() {
        renderText("Hello JFinal World");
    }

    public String test() {
        return "index.html";
    }
}

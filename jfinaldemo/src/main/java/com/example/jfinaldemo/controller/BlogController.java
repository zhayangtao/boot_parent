package com.example.jfinaldemo.controller;

import com.example.jfinaldemo.interceptor.DemoInterceptor;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/02/23
 */
@Before(DemoInterceptor.class) // 类级别拦截器，拦截类所有方法
public class BlogController extends Controller {

    @Before({DemoInterceptor.class})// 方法级别拦截器
    public void index() {

    }

    public void show() {

    }
}

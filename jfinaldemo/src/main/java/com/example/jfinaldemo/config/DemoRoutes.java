package com.example.jfinaldemo.config;

import com.example.jfinaldemo.interceptor.DemoInterceptor;
import com.jfinal.config.Routes;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/02/23
 */
public class DemoRoutes extends Routes {
    @Override
    public void config() {
        addInterceptor(new DemoInterceptor());
    }
}

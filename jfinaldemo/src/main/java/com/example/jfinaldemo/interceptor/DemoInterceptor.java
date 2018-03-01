package com.example.jfinaldemo.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/02/23
 */
public class DemoInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation inv) {
        System.out.println("Before method invoking");
        inv.invoke();
        System.out.println("After method invoking");
    }
}

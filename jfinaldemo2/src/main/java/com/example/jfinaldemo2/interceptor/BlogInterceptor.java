package com.example.jfinaldemo2.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/02/27
 */
public class BlogInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation inv) {
        System.out.println("invocate BlogInterceptor before  method:" + inv.getMethodName());
        inv.invoke();
        System.out.println("invocate BlogInterceptor after  method:" + inv.getMethodName());
    }
}

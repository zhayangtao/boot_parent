package com.example.jfinaldemo2.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/02/26
 */
public class UserInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation inv) {
        System.out.println("Before userController: " + inv.getMethodName());
        inv.invoke();
        System.out.println("after userController: " + inv.getMethodName());
    }
}

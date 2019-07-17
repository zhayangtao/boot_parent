package com.example.boot_start_learning.chapter5_spring_aop;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/16
 * 前置通知
 */
public class SimpleBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("before '" + method.getName() + "', tune guiter");
    }

    public static void main(String[] args) {
        Guitarist john = new Guitarist();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new SimpleBeforeAdvice());
        proxyFactory.setTarget(john);

        Guitarist proxy = (Guitarist) proxyFactory.getProxy();
        proxy.sing();
    }

}

package com.example.boot_start_learning.chapter5_spring_aop;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/16
 * 后置返回通知
 */
public class SimpleAfterReturnAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object target) throws Throwable {
        System.out.println("After '" + method.getName() + "' put down guiter.");
    }

    public static void main(String[] args) {
        Guitarist target = new Guitarist();
        ProxyFactory proxyFactory = new ProxyFactory();

        proxyFactory.addAdvice(new SimpleAfterReturnAdvice());
        proxyFactory.setTarget(target);

        Guitarist proxy = (Guitarist) proxyFactory.getProxy();
        proxy.sing();
    }
}

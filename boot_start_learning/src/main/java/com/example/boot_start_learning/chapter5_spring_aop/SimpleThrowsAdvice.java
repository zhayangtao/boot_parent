package com.example.boot_start_learning.chapter5_spring_aop;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/16
 */
public class SimpleThrowsAdvice implements ThrowsAdvice {
    public static void main(String[] args) {
        ErrorBean errorBean = new ErrorBean();
        ProxyFactory factory = new ProxyFactory();

        factory.setTarget(errorBean);
        factory.addAdvice(new SimpleThrowsAdvice());

        ErrorBean proxy = (ErrorBean) factory.getProxy();

        try {
            proxy.errorProneMethod();
        } catch (Exception e) {

        }

        try {
            proxy.otherErrorProneMethod();
        } catch (Exception e) {
        }
    }

    public void afterThrowing(Exception e) {
        System.out.println("***");
        System.out.println("Generic Exception Capture");
        System.out.println("Caught: " + e.getClass().getName());
        System.out.println("***\n");
    }

    public void afterThrowing(Method method, Object args, Object target, IllegalArgumentException e) {
        System.out.println("***");
        System.out.println("IllegalArgumentException Exception Capture");
        System.out.println("Caught: " + e.getClass().getName());
        System.out.println("Method: " + method.getName());
        System.out.println("***\n");
    }
}

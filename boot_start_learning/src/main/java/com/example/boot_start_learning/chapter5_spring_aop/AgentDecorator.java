package com.example.boot_start_learning.chapter5_spring_aop;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/16
 */
public class AgentDecorator implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.print("James ");
        Object retVal = methodInvocation.proceed();
        System.out.print("!");
        return retVal;
    }
}

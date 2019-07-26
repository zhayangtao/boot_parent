package com.example.boot_start_learning.chapter5_spring_aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/17
 * 使用控制流切入点
 */
public class ControlFlowDemo {

    private class SimpleBeforeAdvice implements MethodBeforeAdvice {

        @Override
        public void before(Method method, Object[] objects, Object o) throws Throwable {
            System.out.println("before method: " + method);
        }
    }
}



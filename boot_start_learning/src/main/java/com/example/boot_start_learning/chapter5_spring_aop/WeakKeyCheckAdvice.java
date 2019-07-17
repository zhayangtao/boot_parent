package com.example.boot_start_learning.chapter5_spring_aop;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

import static com.example.boot_start_learning.chapter5_spring_aop.KeyGenerator.WEAK_KEY;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/16
 */
public class WeakKeyCheckAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] objects, Object target) throws Throwable {
        if (target instanceof KeyGenerator && "getKey".equals(method.getName())) {
            long key = (Long)returnValue;

            if (key == WEAK_KEY) {
                throw new SecurityException("Key Generator generated a weak key. Try again");
            }
        }
    }
}

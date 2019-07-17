package com.example.boot_start_learning.chapter5_spring_aop;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/16
 * 动态切入点
 */
public class SimpleDynamicPointcut extends DynamicMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> aClass, Object... objects) {
        System.out.println("Dynamic check for " + method.getName());
        int x= (Integer) objects[0];
        return x != 100;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        System.out.println("Status check for " + method.getName());
        return "foo".equals(method.getName());
    }

    @Override
    public ClassFilter getClassFilter() {
        return aClass -> aClass == SampleBean.class;
    }
}

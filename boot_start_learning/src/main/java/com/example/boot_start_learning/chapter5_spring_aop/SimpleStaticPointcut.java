package com.example.boot_start_learning.chapter5_spring_aop;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/16
 * 静态切入点
 */
public class SimpleStaticPointcut extends StaticMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> aClass) {
        return "sing".equals(method.getName());
    }

    @Override
    public ClassFilter getClassFilter() {
        return aClass -> (aClass == GoodGuitarist.class);
    }
}

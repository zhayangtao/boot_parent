package com.example.boot_start_learning.chapter5_spring_aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/16
 */
public class ProfilingInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(methodInvocation.getMethod().getName());
        Object returnValue = methodInvocation.proceed();
        stopWatch.stop();
        dumpInfo(methodInvocation, stopWatch.getTotalTimeMillis());
        return returnValue;
    }

    private void dumpInfo(MethodInvocation invocation, long ms) {
        Method method = invocation.getMethod();
        Object target = invocation.getThis();
        Object[] args = invocation.getArguments();
        System.out.println("Executed method: " + method.getName());
        System.out.println("On object of type: " + target.getClass().getName());
        System.out.println("With arguments:");
        Arrays.stream(args).forEach(o -> System.out.println("> " + o));
        System.out.print("\n");
        System.out.println("Took: " + ms + " ms");
    }
}

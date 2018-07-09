package com.example.boot_13_redis.jpa;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/07
 */
@Component
@Aspect
public class WebLogAspect {
    @Pointcut("execution(public * com.example.boot_13_redis..*(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void webBefore() {
        System.out.println("在更新之前");
        System.out.println("stop");
    }

    @After("webLog()")
    public void webAfter() {
        System.out.println("在更新之后");
    }

    @Around("webLog()")
    public void webAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕同志前");
        proceedingJoinPoint.proceed();
        System.out.println("环绕通知后……");
    }

    @AfterReturning("webLog()")
    public void webAfterReturn() {
        System.out.println("afterReturning ……");
    }
}

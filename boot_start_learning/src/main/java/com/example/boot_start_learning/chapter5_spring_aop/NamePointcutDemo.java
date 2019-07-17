package com.example.boot_start_learning.chapter5_spring_aop;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/17
 * 使用简单名称匹配
 */
public class NamePointcutDemo {
    public static void main(String[] args) {
        GrammyGuitarist john = new GrammyGuitarist();

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.addMethodName("sing");
        pointcut.addMethodName("rest");

        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(john);
        proxyFactory.addAdvisor(advisor);

        GrammyGuitarist proxy = (GrammyGuitarist) proxyFactory.getProxy();
        proxy.sing();

        proxy.sing(new Guitar());
        proxy.rest();
        proxy.talk();
    }
}

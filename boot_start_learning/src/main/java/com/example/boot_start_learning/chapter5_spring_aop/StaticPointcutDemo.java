package com.example.boot_start_learning.chapter5_spring_aop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/16
 */
public class StaticPointcutDemo {
    public static void main(String[] args) {
        GoodGuitarist john = new GoodGuitarist();
        GreatGuitarist eric = new GreatGuitarist();
        Singer proxyOne;
        Singer proxyTwo;

        Pointcut pointcut = new SimpleStaticPointcut();
        Advice advice = new SimpleAdvice();
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setTarget(john);
        proxyOne = (Singer) proxyFactory.getProxy();

        proxyFactory = new ProxyFactory();
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setTarget(eric);
        proxyTwo = (Singer) proxyFactory.getProxy();

        proxyOne.sing();
        proxyTwo.sing();
    }
}

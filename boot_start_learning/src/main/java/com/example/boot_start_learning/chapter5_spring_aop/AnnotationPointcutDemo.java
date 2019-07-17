package com.example.boot_start_learning.chapter5_spring_aop;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/17
 */
public class AnnotationPointcutDemo {
    public static void main(String[] args) {
        AdviceGuitarist john = new AdviceGuitarist();
        AnnotationMatchingPointcut pointcut = AnnotationMatchingPointcut.forMethodAnnotation(AdviceRequired.class);
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(john);
        proxyFactory.addAdvisor(advisor);

        AdviceGuitarist proxy = (AdviceGuitarist) proxyFactory.getProxy();
        proxy.sing(new Guitar());
        proxy.rest();
    }
}

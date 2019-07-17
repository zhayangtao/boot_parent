package com.example.boot_start_learning.chapter5_spring_aop;

import org.springframework.aop.framework.ProxyFactory;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/16
 */
public class AgentAOPDemo {
    public static void main(String[] args) {
        Agent agent = new Agent();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new AgentDecorator());
        proxyFactory.setTarget(agent);

        Agent proxy = (Agent) proxyFactory.getProxy();
        agent.speak();
        System.out.println();
        proxy.speak();
    }
}

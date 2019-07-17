package com.example.boot_start_learning.chapter5_spring_aop;

import org.springframework.aop.framework.ProxyFactory;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/16
 */
public class ProfilingDemo {
    public static void main(String[] args) {
        WorkerBean bean = getWorkerBean();
        bean.doSomeWork(100000000);
    }

    private static WorkerBean getWorkerBean() {
        WorkerBean target = new WorkerBean();
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvice(new ProfilingInterceptor());

        return (WorkerBean) factory.getProxy();
    }
}

package com.example.boot.cglib;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;

import java.util.Map;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/05/12
 */
public class BeanSelfProxyAwareMounter implements ApplicationContextAware, SystemBootAddon {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void OnReady() {
        Map<String, BeanSelfProxyAware> proxyAwareMap = applicationContext.getBeansOfType(BeanSelfProxyAware.class);
        proxyAwareMap.values().forEach(beanSelfProxyAware -> {
            beanSelfProxyAware.setSelfProxy(beanSelfProxyAware);
            System.out.println("注册自身被代理的实例");
        });
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}

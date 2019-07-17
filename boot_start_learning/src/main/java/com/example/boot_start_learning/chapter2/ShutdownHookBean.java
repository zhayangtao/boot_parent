package com.example.boot_start_learning.chapter2;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/11
 */
public class ShutdownHookBean implements ApplicationContextAware {
    private ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (context instanceof GenericApplicationContext) {
            ((GenericApplicationContext) context).registerShutdownHook();
        }
    }
}

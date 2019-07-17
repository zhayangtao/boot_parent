package com.example.boot_start_learning.chapter4;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/12
 */
public class Publisher implements ApplicationContextAware {
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public void publish(String message) {
        context.publishEvent(new MessageEvent(this, message));
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
        Publisher publisher = context.getBean("publisher", Publisher.class);
        publisher.publish("I send an SOS to the world...");
        publisher.publish("... I hope that someone gets my...");
        publisher.publish("... Message in a bottle");
    }
}

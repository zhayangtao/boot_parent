package com.example.boot_start_learning.chapter4;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/11
 */
public class MessageDigestDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("app-context.xml");
        context.refresh();
        MessageDigester digester = context.getBean("digester", MessageDigester.class);
        digester.digest("Hello World");
        context.close();
    }
}

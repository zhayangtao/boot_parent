package com.example.boot_start_learning.chapter4;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.security.MessageDigest;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/11
 * 直接访问 FactoryBean
 */
public class AccessingFactoryBeans {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("app-context.xml");
        context.refresh();
        MessageDigest digester = context.getBean("shaDigest", MessageDigest.class);
        System.out.println("digester=" + digester);
        MessageDigestFactoryBean factoryBean = context.getBean("&shaDigest", MessageDigestFactoryBean.class);

        try {
            MessageDigest shaDigest = factoryBean.getObject();
            System.out.println(shaDigest.digest("Hello World".getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        context.close();
    }
}

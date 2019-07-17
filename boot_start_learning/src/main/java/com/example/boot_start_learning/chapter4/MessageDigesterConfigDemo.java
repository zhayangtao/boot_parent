package com.example.boot_start_learning.chapter4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/11
 */
public class MessageDigesterConfigDemo {
    @Configuration
    static class MessageDigesterConfig {
        @Bean
        public MessageDigestFactoryBean shaDigest() {
            MessageDigestFactoryBean factoryBean = new MessageDigestFactoryBean();
            factoryBean.setAlgorithmName("SHA1");
            return factoryBean;
        }

        @Bean
        public MessageDigestFactoryBean defaultDigest() {
            return new MessageDigestFactoryBean();
        }

        @Bean
        MessageDigester digester() throws Exception {
            MessageDigester messageDigester = new MessageDigester();
            messageDigester.setDigest1(shaDigest().getObject());
            messageDigester.setDigest2(defaultDigest().getObject());
            return messageDigester;
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MessageDigesterConfig.class);
        MessageDigester messageDigester = context.getBean("digester", MessageDigester.class);
        messageDigester.digest("Hello World");
        context.close();
    }
}

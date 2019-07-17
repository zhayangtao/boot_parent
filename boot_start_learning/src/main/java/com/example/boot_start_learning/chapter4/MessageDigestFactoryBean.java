package com.example.boot_start_learning.chapter4;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.security.MessageDigest;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/11
 */
public class MessageDigestFactoryBean implements FactoryBean<MessageDigest> , InitializingBean {
    private String algorithmName = "MD5";
    private MessageDigest messageDigest;

    @Override
    public MessageDigest getObject() throws Exception {
        return messageDigest;
    }

    @Override
    public Class<?> getObjectType() {
        return MessageDigest.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        messageDigest = MessageDigest.getInstance(algorithmName);
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }
}

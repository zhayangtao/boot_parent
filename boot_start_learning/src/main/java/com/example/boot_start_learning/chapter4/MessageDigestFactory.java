package com.example.boot_start_learning.chapter4;

import lombok.Setter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/11
 */
@Setter
public class MessageDigestFactory {
    private String algorithmName = "MD5";

    public MessageDigest createInstance() throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(algorithmName);
    }

}

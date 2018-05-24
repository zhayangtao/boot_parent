package com.example.boot.utils;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/05/11
 */
public class DESUtils {

    private static Key key;
    private static String KEY_STR = "myKey";

    static {
        try {
            KeyGenerator generator = KeyGenerator.getInstance("DES");
            generator.init(new SecureRandom(KEY_STR.getBytes()));
            key = generator.generateKey();
            generator = null;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加密字符串
     * @param str
     * @return
     */
    public static String encryptString(String str) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        try {
            byte[] bytes = str.getBytes("UTF8");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptBytes = cipher.doFinal(bytes);
            return base64Encoder.encode(encryptBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

package com.example.boot_webflux.functions;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/27
 */
public class Base64s {
    public static void main(String[] args) {
        final String text = "base64 finally in java 8";
        final String encoded = Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
        System.out.println(encoded);
        final String decoded = new String(Base64.getDecoder().decode(encoded), StandardCharsets.UTF_8);
        System.out.println(decoded);
    }
}

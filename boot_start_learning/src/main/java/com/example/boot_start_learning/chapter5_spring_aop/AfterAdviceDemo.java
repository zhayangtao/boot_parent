package com.example.boot_start_learning.chapter5_spring_aop;

import org.springframework.aop.framework.ProxyFactory;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/16
 */
public class AfterAdviceDemo {
    private static KeyGenerator getKeyGenerator() {
        KeyGenerator target = new KeyGenerator();
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvice(new WeakKeyCheckAdvice());

        return (KeyGenerator) factory.getProxy();
    }

    public static void main(String[] args) {
        KeyGenerator keyGenerator = getKeyGenerator();
        for (int i = 0; i < 10; i++) {
            try {
                long key = keyGenerator.getKey();
                System.out.println("Key: " + key);
            } catch (Exception e) {
                System.out.println("Weak key Generated");
            }

        }
    }
}

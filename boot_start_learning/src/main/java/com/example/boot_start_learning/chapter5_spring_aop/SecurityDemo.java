package com.example.boot_start_learning.chapter5_spring_aop;

import org.springframework.aop.framework.ProxyFactory;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/16
 */
public class SecurityDemo {
    public static void main(String[] args) {
        SecurityManager manager = new SecurityManager();
        SecureBean bean = getSecureBean();

        manager.login("John", "pwd");
        bean.writeSecureMessage();
        manager.logout();

        try {
            manager.login("Invalid user", "pwd");
            bean.writeSecureMessage();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.logout();
        }

        try {
            bean.writeSecureMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SecureBean getSecureBean() {
        SecureBean target = new SecureBean();
        SecurityAdvice advice = new SecurityAdvice();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvice(advice);

        return (SecureBean) factory.getProxy();
    }
}

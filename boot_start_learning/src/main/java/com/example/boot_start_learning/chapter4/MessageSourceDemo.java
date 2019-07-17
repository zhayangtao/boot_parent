package com.example.boot_start_learning.chapter4;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Locale;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/12
 */
public class MessageSourceDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("app-context.xml");
        context.refresh();

        Locale english = Locale.ENGLISH;
        Locale german = new Locale("de", "DE");

        System.out.println(context.getMessage("msg", null, english));
        System.out.println(context.getMessage("msg", null, german));

        System.out.println(context.getMessage("nameMsg", new Object[]{"john", "mayer"}, english));
        System.out.println(context.getMessage("nameMsg", new Object[]{"john", "mayer"}, german));

        context.close();
    }
}

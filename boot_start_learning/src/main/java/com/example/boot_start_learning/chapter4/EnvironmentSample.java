package com.example.boot_start_learning.chapter4;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/12
 */
public class EnvironmentSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.refresh();

        ConfigurableEnvironment configurableEnvironment = context.getEnvironment();
        MutablePropertySources propertySources = configurableEnvironment.getPropertySources();

        Map<String, Object> appMap = new HashMap<>();
        appMap.put("user.home", "application_home");

        propertySources.addLast(new MapPropertySource("prospring5 MAP", appMap));
        System.out.println("user.home:" + System.getProperty("user.home"));
        System.out.println("JAVA_HOME:" + System.getenv("JAVA_HOME"));

        System.out.println("user.home:" + configurableEnvironment.getProperty("user.home"));
        System.out.println("JAVA_HOME:" + configurableEnvironment.getProperty("JAVA_HOME"));
        context.close();
    }
}

package com.example.boot_start_learning.chapter4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/12
 * 访问文件资源
 */
public class ResourceDemo {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext();
        File file = File.createTempFile("test20190712", "txt");
        file.deleteOnExit();
        Resource resource1 = context.getResource("file://" + file.getPath());
        displayInfo(resource1);
        Resource resource2 = context.getResource("classpath:test20190712.txt");
        displayInfo(resource2);
        Resource resource3 = context.getResource("http://www.baidu.com");
        displayInfo(resource3);

    }

    private static void displayInfo(Resource resource) throws IOException {
        System.out.println(resource.getClass());
        System.out.println(resource.getURL().getContent());
        System.out.println();
    }
}

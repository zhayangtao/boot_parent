package com.example.boot_start_learning.chapter11_task;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/30
 */
public class AsyncTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskConfig.class);
        AsyncService asyncService = context.getBean(AsyncService.class);
        for (int i = 0; i < 5; i++) {
            asyncService.asyncTask();
        }

        Future<String> result1 = asyncService.asyncWithReturn("Jone Mayer");
        Future<String> result2 = asyncService.asyncWithReturn("Eric Clapton");
        Future<String> result3 = asyncService.asyncWithReturn("BB King");

        System.out.println(result1.get());
        System.out.println(result2.get());
        System.out.println(result3.get());

        System.in.read();
        context.close();
    }
}

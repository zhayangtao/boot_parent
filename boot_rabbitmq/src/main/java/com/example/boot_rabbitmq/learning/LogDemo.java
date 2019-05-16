package com.example.boot_rabbitmq.learning;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/25
 */
public class LogDemo {
    private static final Logger logger = Logger.getLogger(LogDemo.class.getName());

    @Override
    public String toString() {
        System.out.println("toString");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.fine(() -> "打印一下日志：" + this);
        return "LogDemo";
    }

    private void test() {
        logger.fine("打印一些日志：" + this);
    }

    public static void main(String[] args) {
        LogDemo demo = new LogDemo();
        demo.test();
    }
}

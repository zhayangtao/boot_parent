package com.example.boot.threads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/20
 */
public class ThreadFactoryTest {

    static class MyThreadFactory implements ThreadFactory {

        private int counter;
        private String name;
        private List<String> stats;

        MyThreadFactory(String name) {
            counter = 0;
            this.name = name;
            stats = new ArrayList<>();
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, name + "-Thread-" + counter);
            counter++;
            stats.add(String.format("Created thead %d with name %s on %s\n", t.getId(), t.getName(), new Date()));
            return t;
        }

        String getStats() {
            StringBuilder buffer = new StringBuilder();
            for (String stat : stats) {
                buffer.append(stat);
                buffer.append("\n");
            }
            return buffer.toString();

        }
    }

    static class MyTask implements Runnable {
        private int num;

        MyTask(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            System.out.println("Task " + num + " is running");
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("main thread begin");
        MyThreadFactory factory = new MyThreadFactory("MyThreadFactory");
        
        Thread thread = null;
        for (int i = 0; i < 10; i++) {
            thread = factory.newThread(new MyTask(i));
            thread.start();
        }
        System.out.println("Factory stats:\n");
        System.out.printf("%s\n", factory.getStats());
        System.out.println("main thread end");
    }
}

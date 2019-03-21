package com.example.boot_15.threads;

import java.util.ArrayList;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/02/18
 */
public class SynchronizedTest {

    public static void main(String[] args) {
        final InsertData insertData = new InsertData();
        new Thread(() -> insertData.insert(Thread.currentThread())).start();

        new Thread(() -> insertData.insert(Thread.currentThread())).start();
    }

    private static class InsertData {
        private ArrayList<Integer> list = new ArrayList<>();

        public void insert(Thread thread) {
            for (int i = 0; i < 5; i++) {
                System.out.println(thread.getName() + "插入数据" + i);
                list.add(i);
            }
        }
    }

}

package com.example.boot_15.threads;

import java.util.ArrayList;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/02/19
 */
public class CollectionTest {

    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        /*Vector<Integer> vector = new Vector<>();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Arraylist:" + (end - start) + "ms");
        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++)
            vector.add(i);
        end = System.currentTimeMillis();
        System.out.println("Vector进行100000次插入操作耗时：" + (end - start) + "ms");*/
        list.add(2);
        list.removeIf(integer -> integer == 2);

        iteratorTest();

    }

    public static void iteratorTest() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        new Thread(() -> {
            for (Integer integer : list) {
                System.out.println(integer);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            list.removeIf(integer -> integer == 2);
        }).start();
    }
}

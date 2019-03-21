package com.example.boot_17;

import org.junit.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/20
 */
public class ConcurrentSkipListSetTest {
    private static Set<String> set = new ConcurrentSkipListSet<>();

    @Test
    public void test() {
        new MyThead("a").start();
        new MyThead("b").start();
    }


    private static class MyThead extends Thread {
        MyThead(String name) {
            super(name);
        }

        @Override
        public void run() {
            int i = 0;
            while (i++ < 10) {
                String val = Thread.currentThread().getName() + (i % 6);
                set.add(val);
                printAll();
            }
        }
    }
    private static void printAll() {
        for (String s : set) {
            System.out.println(s + ",");
        }
        System.out.println();
    }

    @Test
    public void test1() {
        int a = 1;
        int b = 2;
        String a1 = "a";
        String b1 = "b";
        System.out.println(a !=(a = b));
        System.out.println((a = b) !=a);
        System.out.println(a1 !=(a1 = b1));

        Demo demo1 = new Demo(1);
        Demo demo2 = new Demo(1);

        System.out.println(demo1 !=(demo1 = demo2));
    }

    private class Demo {
        private int name;

        Demo(int name) {
            this.name = name;
        }

        public int getName() {
            return name;
        }

        public void setName(int name) {
            this.name = name;
        }
    }
}

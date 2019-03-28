package com.example.boot_akka;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/22
 */
public class GoodSuspend {
    private static Object u = new Object();

    static class ChangeObjectThread extends Thread {
        volatile boolean suspendMe = false;
        void suspendMe() {
            suspendMe = true;
        }

        void resumeMe() {
            suspendMe = false;
            synchronized (this) {
                notify();
            }
        }

        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    while (suspendMe) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                synchronized (u) {
                    System.out.println("in ChangeObjectThread");
                }
                Thread.yield();
            }
        }
    }
    static class ReadObjectThread extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (u) {
                    System.out.println("in ReadObjectThread");
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ChangeObjectThread changeObjectThread = new ChangeObjectThread();
        ReadObjectThread readObjectThread = new ReadObjectThread();
        changeObjectThread.start();
        readObjectThread.start();
        Thread.sleep(1000);
        changeObjectThread.suspendMe();
        System.out.println("suspend changeObjectThread 2 sec");
        Thread.sleep(2000);
        System.out.println("resume changeObjectThread");
        changeObjectThread.resumeMe();
    }
}

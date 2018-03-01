package com.example.boot.temp;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/1/3
 */
public class SyncDubbo {

    private static class Main {
        int i = 5;
        public synchronized void operationSup() {
            i--;
            System.out.println("main print i = " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private static class Sub extends Main {
        @Override
        public synchronized void operationSup() {
            while (i > 0) {
                i--;
                System.out.println("sub print i = " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Sub sub = new Sub();
                sub.operationSup();
            }
        }).start();
    }
}

package com.example.boot_akka;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/02
 */
public class FalseSharing implements Runnable {
    private final static int NUM_ThREADS = 2;
    private final static long ITERATIONS = 500L * 1000 * 1000;
    private final int arrayIndex;
    private static VolatileLong[] longs = new VolatileLong[NUM_ThREADS];

    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong();
        }
    }

    public FalseSharing(int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(String[] args) throws InterruptedException {
        final long start = System.currentTimeMillis();
        runTest();
        System.out.println("duration=" + (System.currentTimeMillis() - start));
    }

    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_ThREADS];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharing(i));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }

    @Override
    public void run() {

    }

    private static class VolatileLong {
        private volatile long value = 0L;
//        private long p1, p2, p3, p4, p5, p6, p7;
    }

}

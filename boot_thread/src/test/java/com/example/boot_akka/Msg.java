package com.example.boot_akka;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/03
 */
public class Msg {
    private double i;
    private double j;
    private String orgStr = null;

    public double getI() {
        return i;
    }

    public void setI(double i) {
        this.i = i;
    }

    public double getJ() {
        return j;
    }

    public void setJ(double j) {
        this.j = j;
    }

    public String getOrgStr() {
        return orgStr;
    }

    public void setOrgStr(String orgStr) {
        this.orgStr = orgStr;
    }

    /**
     * P1 计算加法
     */
    private static class Plus implements Runnable {
        private static BlockingQueue<Msg> blockingQueue = new LinkedBlockingQueue<>();

        @Override
        public void run() {
            while (true) {
                try {
                    Msg msg = blockingQueue.take();
                    msg.setJ(msg.getI() + msg.getJ());
                    Multiply.blockingQueue.add(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * P2 计算乘法
     */
    private static class Multiply implements Runnable{
        private static BlockingQueue<Msg> blockingQueue = new LinkedBlockingQueue<>();

        @Override
        public void run() {
            while (true) {
                try {
                    Msg msg = blockingQueue.take();
                    msg.setI(msg.getI() * msg.getJ());
                    Div.blockingQueue.add(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * P3 计算除法
     */
    private static class Div implements Runnable {
        private static BlockingQueue<Msg> blockingQueue = new LinkedBlockingQueue<>();

        @Override
        public void run() {
            while (true) {
                try {
                    Msg msg = blockingQueue.take();
                    msg.setI(msg.getI() / 2);
                    System.out.println(msg.getOrgStr() + "=" + msg.getI());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Plus()).start();
        new Thread(new Multiply()).start();
        new Thread(new Div()).start();
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                Msg msg = new Msg();
                msg.setI(i);
                msg.setJ(j);
                msg.setOrgStr("((" + i + "+" + j + ")*" + i + ")/2");
                Plus.blockingQueue.add(msg);
            }
        }
    }

}

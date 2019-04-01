package com.example.boot_akka;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/29
 * 生产者消费者模式
 */
public class ProducerDemo implements Runnable {
    private volatile boolean isRunning = true;
    private BlockingQueue<PCData> queue; // 内存缓冲区
    private static AtomicInteger count = new AtomicInteger(); // 总数，原子操作
    private static final int SLEEPTIME = 1000;

    private ProducerDemo(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random random = new Random();
        System.out.println("start producer id=" + Thread.currentThread().getId());
        try {
            while (isRunning) {
                Thread.sleep(random.nextInt(SLEEPTIME));
                PCData pcdata = new PCData(count.incrementAndGet());// 构造任务数据
                System.out.println(pcdata + " is put into queue");
                if (!queue.offer(pcdata, 2, TimeUnit.SECONDS)) { // 提交数据到缓冲区
                    System.out.println("failed to put data:" + pcdata);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    private void stop() {
        isRunning = false;
    }

    private class PCData {
        private final int intData;

        PCData(int intData) {
            this.intData = intData;
        }

        int getIntData() {
            return intData;
        }

        @Override
        public String toString() {
            return "PCDate{" +
                    "intData=" + intData +
                    '}';
        }
    }

    /**
     * 消费者
     */
    private static class ConsumerDemo implements Runnable {
        private BlockingQueue<PCData> queue; // 缓冲区
        private static final int SLEEPTIME = 1000;

        ConsumerDemo(BlockingQueue<PCData> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            System.out.println("start Consumer id=" + Thread.currentThread().getId());
            Random random = new Random(); // 随机等待时间
            try {
                while (true) {
                    PCData data = queue.take();
                    int re = data.getIntData() * data.getIntData(); // 计算平方
                    System.out.println(MessageFormat.format("{0}*{1}={2}", data.getIntData(), data.getIntData(), re));
                    Thread.sleep(random.nextInt(SLEEPTIME));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<PCData> queue = new LinkedBlockingQueue<>(10);
        ProducerDemo producerDemo1 = new ProducerDemo(queue); // 建立生产者
        ProducerDemo producerDemo2 = new ProducerDemo(queue); // 建立生产者
        ProducerDemo producerDemo3 = new ProducerDemo(queue); // 建立生产者

        ConsumerDemo consumerDemo1 = new ConsumerDemo(queue); // 建立消费者
        ConsumerDemo consumerDemo2 = new ConsumerDemo(queue);
        ConsumerDemo consumerDemo3 = new ConsumerDemo(queue);

        ExecutorService executorService = Executors.newCachedThreadPool(); // 建立线程池
        executorService.execute(producerDemo1); // 运行生产者
        executorService.execute(producerDemo2);
        executorService.execute(producerDemo3);

        executorService.execute(consumerDemo1); // 运行消费者
        executorService.execute(consumerDemo2);
        executorService.execute(consumerDemo3);

        Thread.sleep(10 * 1000);
        producerDemo1.stop();
        producerDemo2.stop();
        producerDemo3.stop();

        Thread.sleep(3000);
        executorService.shutdown();
    }
}

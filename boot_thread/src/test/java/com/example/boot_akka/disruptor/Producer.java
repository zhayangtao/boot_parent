package com.example.boot_akka.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/01
 */
public class Producer {
    private final RingBuffer<PCData> ringBuffer;

    private Producer(RingBuffer<PCData> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private void pushData(ByteBuffer byteBuffer) {
        long sequence = ringBuffer.next();

        try {
            PCData event = ringBuffer.get(sequence);
            event.setValue(byteBuffer.getLong(0));
        } finally {
            ringBuffer.publish(sequence);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        PCDataFactory factory = new PCDataFactory();

        int bufferSize = 1024; // ringBuffer 的大小必须为 2 的 次方倍
        Disruptor<PCData> dataDisruptor = new Disruptor<>(factory, bufferSize,
                executorService, ProducerType.MULTI, new BlockingWaitStrategy());
        dataDisruptor.handleEventsWithWorkerPool(new Consumer(),
                new Consumer(), new Consumer(), new Consumer());
        dataDisruptor.start();

        RingBuffer<PCData> ringBuffer = dataDisruptor.getRingBuffer();
        Producer producer = new Producer(ringBuffer);
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (long i = 0; true ; i++) {
            byteBuffer.putLong(0, i);
            producer.pushData(byteBuffer);
            Thread.sleep(100);
            System.out.println("add data " + i);
        }
    }
}

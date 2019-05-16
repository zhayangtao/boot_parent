package com.example.boot_akka.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/01
 * 使用Java8  的 Disruptor
 */
public class LongEventMain {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        int bufferSize = 1024;
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, executorService);

        disruptor.handleEventsWith((event, l, b) -> System.out.println("Event: " + event.getValue()));
        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        ByteBuffer byteBuffer = ByteBuffer.allocate(8);

        for (long i = 0; i < 10; i++) {
            byteBuffer.putLong(0, i);
            ringBuffer.publishEvent((event, sequence, buffer) -> event.set(buffer.getLong(0)), byteBuffer);
            Thread.sleep(1000);
        }
        executorService.shutdown();
    }
}

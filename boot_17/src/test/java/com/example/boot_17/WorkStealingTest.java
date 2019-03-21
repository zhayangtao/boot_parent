package com.example.boot_17;

import com.google.common.util.concurrent.AtomicLongMap;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/20
 */
public class WorkStealingTest {
    //    public static final WorkStealingChannel
    public class WorkStealingChannel<T> {
        BlockingDeque<T>[] managedQueues;
        AtomicLongMap<Integer> stat = AtomicLongMap.create();

        public WorkStealingChannel() {
            int nCpu = Runtime.getRuntime().availableProcessors();
            int queueCount = nCpu >> 1 + 1;
            managedQueues = new LinkedBlockingDeque[queueCount];
            for (int i = 0; i < queueCount; i++) {
                managedQueues[i] = new LinkedBlockingDeque<>();
            }
        }

        public void put(T item) throws InterruptedException {
            int targetIndex = Math.abs(item.hashCode() % managedQueues.length);
            BlockingDeque<T> targetQueue = managedQueues[targetIndex];
            targetQueue.put(item);
        }

        public T take() throws InterruptedException {
            int rdnInx = ThreadLocalRandom.current().nextInt(managedQueues.length);
            int idx = rdnInx;
            do {
                idx = idx % managedQueues.length;
                T item = null;
                if (idx == rdnInx) {
                    item = managedQueues[idx].poll();
                } else {
                    item = managedQueues[idx].pollLast();
                }
                if (item != null) {
                    System.out.println("take element from queue " + idx);
                    stat.addAndGet(idx, 1);
                    return item;
                }
                idx++;
            } while (idx != rdnInx);
            System.out.println("wait for queue:" + rdnInx);
            stat.addAndGet(rdnInx, 1);
            return managedQueues[rdnInx].take();
        }

        public AtomicLongMap<Integer> getStat() {
            return this.stat;
        }
    }

    @Test
    public void test() throws InterruptedException {
        int i = 8;
        System.out.println(8 >> 1);

        System.out.println("===================== newWorkStealingPool - 并行任务线程池");
//定义一个并行工作者线程池
        ExecutorService workStealingPool = Executors.newWorkStealingPool();
        for (int j = 0; j < 20; j++) {
            workStealingPool.submit(() -> System.out.println(Thread.currentThread().getName()));
        }
        Thread.sleep(1000);
        workStealingPool.shutdown();
    }
}

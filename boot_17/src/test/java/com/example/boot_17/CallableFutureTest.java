package com.example.boot_17;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/20
 */
public class CallableFutureTest {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    static class RandomByRunnable implements Runnable {

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicInteger.compareAndSet(0, RandomUtils.nextInt(100, 200));
        }
    }

    static class RandomByCallable implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            TimeUnit.SECONDS.sleep(1);
            return RandomUtils.nextInt(100, 200);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Thread(RandomByRunnable::new).start();
        //通过while去循环查询是否获取了值
//        while (atomicInteger.get() == 0);
        System.out.println("实现Runnable接口的线程（裸线程），获取了结果：" + atomicInteger.get());

        ExecutorService executorService = Executors.newCachedThreadPool();
        // 初始化
        atomicInteger = new AtomicInteger(0);
        executorService.submit(new RandomByRunnable());
        //通过while去循环查询是否获取了值
//        while (atomicInteger.get() == 0);
        System.out.println("实现Runnable接口的线程 + Executor框架，获取了结果：" + atomicInteger.get());

        //实现Callable接口 + Executor框架
        Future<Integer> result = executorService.submit(new RandomByCallable());
        System.out.println("实现Callable接口 + Executor框架，获取了结果：" + result.get());
        executorService.shutdown();
    }

    @Test
    public void test() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 方式1 通过Future + Callable + ExecutorService获取值
        Callable<Integer> callable = () -> {
            TimeUnit.SECONDS.sleep(2);
            return RandomUtils.nextInt(100, 200);
        };
        Future<Integer> future = executorService.submit(callable);
        try {
            System.out.println("方式1：Future + Callable + ExecutorService 计算结果：" + future.get());
        } catch (InterruptedException e) {
            //e.printStackTrace();
            System.out.println("Future 被中断");
        } catch (ExecutionException e) {
            //e.printStackTrace();
            System.out.println("Future 执行出错");
        }
        System.out.println();
        // 方式2：通过FutureTask + Callable + ExecutorService取值 -- FutureTask包装Callable接口
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        executorService.submit(futureTask);
        //获取结果
        try {
            System.out.println("方式2：FutureTask包装Callable接口 + ExecutorService 计算结果：" + futureTask.get());
        } catch (InterruptedException e) {
            //e.printStackTrace();
            System.out.println("FutureTask(ExecutorService) 被中断");
        } catch (ExecutionException e) {
            //e.printStackTrace();
            System.out.println("FutureTask(ExecutorService) 执行出错");
        }
        System.out.println();

        AtomicInteger atomicInteger = new AtomicInteger(0);
        Runnable runnable = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicInteger.compareAndSet(0, RandomUtils.nextInt(100, 200));
        };
        System.out.println();
        //方式4：通过FutureTask + Runnable + ExecutorService 取值 -- FutureTask包装Runnable接口
        FutureTask<AtomicInteger> futureTask1 = new FutureTask<>(runnable, atomicInteger);
        executorService.submit(futureTask1);
        //循环等待结果
        while (atomicInteger.get() == 0) ;
        //输出结果
        System.out.println("方式4：FutureTask包装Runnable接口 + ExecutorService 的计算结果：" + atomicInteger.get());

    }

    @Test
    public void test1() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Callable<Integer>> callableList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            callableList.add(() -> {
                int result = RandomUtils.nextInt(100, 200);
                TimeUnit.MILLISECONDS.sleep(result);
                System.out.println("init " + result);
                return result;
            });
        }
        // 全部任务完成
        List<Future<Integer>> futureList = executorService.invokeAll(callableList);
        System.out.println("等待所有的任务完成之后，才会得到结果集。");
        futureList.forEach(integerFuture -> {
            try {
                System.out.println("result " + integerFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        TimeUnit.SECONDS.sleep(1);
        System.out.println();
        //最先完成任务
        Integer firstResult = executorService.invokeAny(callableList);
        System.out.println("得到一个最先得到的结果，立即返回；后面的任务不再运行。");
        System.out.println("result " + firstResult);
    }

    @Test
    public void test2() {
        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        final Runnable beeper = () -> System.out.println("beep");
        final ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(beeper, 10, 10, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(() -> {
            scheduledFuture.cancel(true);
        }, 60 * 60, TimeUnit.SECONDS);
    }

    /**
     * 练习内容：
     *
     * 分别实现以下调度任务：
     * 方式1：通过schedule方法实现：2秒之后打印系统时间。
     * 方式2：通过scheduleWithFixedDelay方法实现：5秒之后开始周期性的打印系统时间，连续两次打印间隔为3秒(delay)，每次打印耗时2秒。
     * 方式3：通过scheduleAtFixedRate方法实现：5秒之后开始周期性的打印系统时间，每3秒(period)打印一次，每次打印耗时2秒。
     * 方式3：通过scheduleAtFixedRate方法实现：5秒之后开始周期性的打印系统时间，每2秒(period)打印一次，每次打印耗时3秒
     */
    @Test
    public void test3() throws InterruptedException, ExecutionException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        int type = 0;
        switch (type) {
            case 0:
                System.out.println("延时执行Runnable接口 : " + System.currentTimeMillis());
                scheduledExecutorService.schedule(() -> {
                    System.out.println("2秒之后 : " + System.currentTimeMillis());
                }, 2000, TimeUnit.SECONDS);
                TimeUnit.MILLISECONDS.sleep(2500);
                System.out.println();
                System.out.println("延时执行Callable接口 : " + System.currentTimeMillis());
                ScheduledFuture scheduledFuture = scheduledExecutorService.schedule(System::currentTimeMillis, 2, TimeUnit.SECONDS);
                System.out.println("2秒之后 ：" + scheduledFuture.get());
                TimeUnit.MILLISECONDS.sleep(1000);
                break;
            case 1: // 周期性延时执行

                // 初始延时
                long initDelay = 5000;
                // 延时
                long delay = 3000;
                System.out.println("周期性的延时执行Runnable接口 ： " + System.currentTimeMillis());
                // 周期性延时执行
                scheduledExecutorService.scheduleWithFixedDelay(() -> {
                    int num = RandomUtils.nextInt(1000, 3000);
                    System.out.println("周期性的延时执行Runnable接口 [" + num + "]开始运行 ： " + System.currentTimeMillis());
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, initDelay, delay, TimeUnit.MILLISECONDS);
                TimeUnit.SECONDS.sleep(20);
                break;
            case 2:
                // 初始延时
                initDelay = 5000;
                // 延时
                delay = 3000;
                System.out.println("周期性的延时执行Runnable接口 ： " + System.currentTimeMillis());
                // 周期性延时执行
                scheduledExecutorService.scheduleAtFixedRate(() -> {
                    int num = RandomUtils.nextInt(1000, 3000);
                    System.out.println("周期性的延时执行Runnable接口 [" + num + "]开始运行 ： " + System.currentTimeMillis());
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, initDelay, delay, TimeUnit.MILLISECONDS);
                TimeUnit.SECONDS.sleep(20);
                break;
        }
    }

    @Test
    public void test4() throws ExecutionException, InterruptedException {
        // Runnable 转换成 Callable
        final Integer[] result = {null};
        Runnable runnable = () -> result[0] = 111111;
        // 定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        Callable<Object> callable = Executors.callable(runnable);
//        /提交转换成之后的Callable接口
        Future future = executorService.submit(callable);
        // 输出运行结果，肯定是 null
        System.out.println("Executors.callable(runnable) 的future = " + future.get());

        callable = Executors.callable(runnable, result[0]);
        future = executorService.submit(callable);
        //输出运行结果
        System.out.println("Executors.callable(runnable, result) 的future = " + future.get());

    }

    /**
     * ThreadFactory 类
     */
    @Test
    public void test5() {
        ThreadFactory factory = Executors.defaultThreadFactory();
        for (int i = 0; i < 5; i++) {
            factory.newThread(() -> System.out.println(Thread.currentThread() + ":" + 222)).start();
        }
    }

}

package com.example.boot.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/09
 */
public class TaskWithResult implements Callable<String> {

    private int id;

    TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        System.out.println("call() 方法被自动调用：" + Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(4); // 模拟耗时操作
        return "结果: " + id + "  " + Thread.currentThread().getName();
    }

}

class ThreadPool2 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        List<Future<String>> futureList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Future<String> future = service.submit(new TaskWithResult(i));
            futureList.add(future);
        }
        // 启动一次顺序关闭，执行以前提交的任务，但不接受新任务。
        service.shutdown();

        futureList.forEach(stringFuture -> {
            try {
                System.out.println(stringFuture.get()); // 打印任务执行结果
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}

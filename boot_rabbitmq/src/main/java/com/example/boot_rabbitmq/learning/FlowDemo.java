package com.example.boot_rabbitmq.learning;

import java.util.concurrent.Flow.*;
import java.util.concurrent.Flow.Processor;
import java.util.concurrent.SubmissionPublisher;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/26
 * jdk9 的响应式流
 */
public class FlowDemo {
    /**
     * Processor，需要继承 SubmissionPublisher 并实现 Processor 接口
     */
    class MyProcessor extends SubmissionPublisher<String> implements Processor<Integer, String> {
        private Subscription subscription;

        @Override
        public void onSubscribe(Subscription subscription) {
            // 保存订阅关系，需要用它给发布者响应
            this.subscription = subscription;
            // 请求一个数据
            this.subscription.request(1);
        }

        @Override
        public void onNext(Integer item) {
            // 接收到一个数据
            System.out.println("处理器接收到数据：" + item);
            // 过滤小于0的，然后发布
            if (item > 0) {
                this.submit("转换后的数据：" + item);
            }
            // 处理完调用 request 再请求一个数据
            this.subscription.request(1);
        }

        @Override
        public void onError(Throwable throwable) {
            throwable.printStackTrace();
            // 告诉发布者，不再接受数据
            this.subscription.cancel();
        }

        @Override
        public void onComplete() {
            // 全部数据处理完了（发布者关闭了）
            System.out.println("处理器处理完");
            // 关闭发布者
            this.close();
        }
    }

   static class FlowDemo2 {
        public static void main(String[] args) {
            // 定义发布者，发布的数据类型是 Integer
            SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
        }
    }
}

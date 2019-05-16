package com.example.boot_akka.disruptor;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/02
 */
public class MyInParkingDataEvent {
    private String carLicense; // 车牌号

    public String getCarLicense() {
        return carLicense;
    }

    public void setCarLicense(String carLicense) {
        this.carLicense = carLicense;
    }

    /**
     * 第一个消费者
     */
    public static class MyParkingDataInDbHandler implements EventHandler<MyInParkingDataEvent> {

        @Override
        public void onEvent(MyInParkingDataEvent myInParkingDataEvent, long l, boolean b) throws Exception {
            long threadId = Thread.currentThread().getId(); // 获取当前线程id
            String carLicense = myInParkingDataEvent.getCarLicense(); // 获取车牌号
            System.out.println(String.format("Thread Id %s 保存 %s 到数据库中 ....", threadId, carLicense));
        }

        /**
         * 第二个消费者
         */
        public static class MyParkingDataToKafkaHandler implements EventHandler<MyInParkingDataEvent> {
            @Override
            public void onEvent(MyInParkingDataEvent myInParkingDataEvent, long l, boolean b) throws Exception {
                long threadId = Thread.currentThread().getId(); // 获取当前线程id
                String carLicense = myInParkingDataEvent.getCarLicense(); // 获取车牌号
                System.out.println(String.format("Thread Id %s 发送 %s 进入停车场信息给 kafka系统...", threadId, carLicense));
            }
        }

        /**
         * 第三个消费者
         */
        public static class MyParkingDataSmsHandler implements EventHandler<MyInParkingDataEvent> {
            @Override
            public void onEvent(MyInParkingDataEvent myInParkingDataEvent, long l, boolean b) throws Exception {
                long threadId = Thread.currentThread().getId(); // 获取当前线程id
                String carLicense = myInParkingDataEvent.getCarLicense(); // 获取车牌号
                System.out.println(String.format("Thread Id %s 给  %s 的车主发送一条短信，并告知他计费开始了 ....", threadId, carLicense));
            }
        }


    }

    /**
     * 消息生产者
     */
    public static class MyInParkingDataEventPublisher implements Runnable {
        private CountDownLatch countDownLatch;
        private Disruptor<MyInParkingDataEvent> disruptor;
        private static final int MUM = 1;

        public MyInParkingDataEventPublisher(CountDownLatch countDownLatch, Disruptor<MyInParkingDataEvent> disruptor) {
            this.countDownLatch = countDownLatch;
            this.disruptor = disruptor;
        }

        @Override
        public void run() {

        }
    }

    public class MyInParkingDataEventTranslator implements EventTranslatorOneArg<MyInParkingDataEvent, String> {

        @Override
        public void translateTo(MyInParkingDataEvent myInParkingDataEvent, long l, String s) {
            this.generateData(myInParkingDataEvent);
        }

        private void generateData(MyInParkingDataEvent myInParkingDataEvent) {
            myInParkingDataEvent.setCarLicense("车牌号： 鄂A-" + (int)(Math.random() * 100000)); // 随机生成一个车牌号
            System.out.println("Thread Id " + Thread.currentThread().getId() + " 写完一个event");
        }
    }

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        int bufferSize = 2048;
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        // Event 初始化工厂
        Disruptor<MyInParkingDataEvent> disruptor = new Disruptor<>(MyInParkingDataEvent::new, bufferSize,
                executorService, ProducerType.SINGLE, new YieldingWaitStrategy());
        EventHandlerGroup<MyInParkingDataEvent> handlerGroup = disruptor.handleEventsWith(new MyParkingDataInDbHandler(), new MyParkingDataInDbHandler.MyParkingDataToKafkaHandler());

        // 当上面两个消费者处理结束后在消耗 smsHandler
        MyParkingDataInDbHandler.MyParkingDataSmsHandler myParkingDataSmsHandler = new MyParkingDataInDbHandler.MyParkingDataSmsHandler();
        handlerGroup.then(myParkingDataSmsHandler);

        // 启动 disruptor
        disruptor.start();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        // 生产者生成数据
        executorService.submit(new MyInParkingDataEventPublisher(countDownLatch, disruptor));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            disruptor.shutdown();
            executorService.shutdown();
        }

        System.out.println("总耗时:" + (System.currentTimeMillis() - begin));
    };

}

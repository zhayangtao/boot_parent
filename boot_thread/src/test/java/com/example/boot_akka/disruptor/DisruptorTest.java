package com.example.boot_akka.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ThreadFactory;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/29
 */
public class DisruptorTest {
    /**
     * 消息事件类
     */
    private static class MessageEvent {
        /**
         * 原始消息
         */
        private String message;

        String getMessage() {
            return message;
        }

        void setMessage(String message) {
            this.message = message;
        }
    }

    /**
     * 消息事件工厂类
     */
    private static class MessageEventFactory implements EventFactory<MessageEvent> {
        @Override
        public MessageEvent newInstance() {
            return new MessageEvent();
        }
    }

    /**
     * 消息转换类，负责将消息转换成事件
     */
    private static class MessageEventTranslator implements EventTranslatorOneArg<MessageEvent, String> {

        @Override
        public void translateTo(MessageEvent messageEvent, long l, String s) {
            messageEvent.setMessage(s);
        }
    }

    /**
     * 消费者线程工厂类
     */
    private static class MessageThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "Simple Disruptor Test Thread");
        }
    }

    /**
     * 消息事件处理类，这里只打印消息
     */
    private static class MessageEventHandler implements EventHandler<MessageEvent> {

        @Override
        public void onEvent(MessageEvent messageEvent, long l, boolean b) throws Exception {
            System.out.println(messageEvent.getMessage());
        }
    }

    /**
     * 异常处理类
     */
    private static class MessageExceptionHandler implements ExceptionHandler<MessageEvent> {

        @Override
        public void handleEventException(Throwable throwable, long l, MessageEvent messageEvent) {
            throwable.printStackTrace();
        }

        @Override
        public void handleOnStartException(Throwable throwable) {
            throwable.printStackTrace();
        }

        @Override
        public void handleOnShutdownException(Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * 消息生产者
     */
    private static class MessageEventProducer {
        private RingBuffer<MessageEvent> ringBuffer;

        MessageEventProducer(RingBuffer<MessageEvent> ringBuffer) {
            this.ringBuffer = ringBuffer;
        }

        /**
         * 将接收到的消息输出到 ringBuffer
         * @param message
         */
        void onData(String message) {
            EventTranslatorOneArg<MessageEvent, String> translatorOneArg = new MessageEventTranslator();
            ringBuffer.publishEvent(translatorOneArg, message);
        }
    }

    public static void main(String[] args) {
        String message = "Hello Disruptor";
        int ringBufferSize = 1024; // ringBuffer 的大小必须是 2 的 N 次方
        Disruptor<MessageEvent> disruptor = new Disruptor<MessageEvent>
                (new MessageEventFactory(), ringBufferSize,
                        new MessageThreadFactory(), ProducerType.SINGLE,
                        new BlockingWaitStrategy());
        disruptor.handleEventsWith(new MessageEventHandler());
        disruptor.setDefaultExceptionHandler(new MessageExceptionHandler());

        RingBuffer<MessageEvent> ringBuffer = disruptor.start();
        MessageEventProducer producer = new MessageEventProducer(ringBuffer);
        producer.onData(message);
    }


}

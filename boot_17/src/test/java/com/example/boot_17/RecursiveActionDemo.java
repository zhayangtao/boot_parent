package com.example.boot_17;

import org.apache.commons.lang3.RandomUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.RecursiveAction;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/21
 *  * <p>ForkJoin框架实例1-RecursiveAction-无返回值-数据交换</p>
 *  * <p>数据交换：专网A内的数据库DB1上有100万数据，需要通过数据交换服务发送到专网B的数据库DB2上。
 *  * 1.原来的做法：将这100万数据按照5000条一组进行分组，然后每组都通过一个线程进行发送。不知道什么原因，总之经常会出现重复发送的数据。
 *  * 2.新的做法：根据ForkJoin框架编程思想，将这100万数据按照阈值THRESHOLD进行子任务划分，然后依次发送。</p>
 *
 */
public class RecursiveActionDemo {
    public static ConcurrentLinkedQueue db2 = new ConcurrentLinkedQueue();

    private static class DataExchangeTask extends RecursiveAction {
        private static final int THEREHOLD = 5000;
        private int start;
        private int end;
        // 交换的数据
        private List<String> list;

        public DataExchangeTask(int start, int end, List<String> list) {
            this.start = start;
            this.end = end;
            this.list = list;
        }

        @Override
        protected void compute() {
            // 如果当前任务数量在阀值范围内，则发送数据
            if (end - start < THEREHOLD) {
                // 发送数据
                try {
                    sendJsonDate(list);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                // 任务拆分
                int middle = (start + end) / 2;
                DataExchangeTask left = new DataExchangeTask(start, middle, list);
                DataExchangeTask right = new DataExchangeTask(middle + 1, end, list);
                left.fork();
                right.fork();
            }
        }
        /**
         * <p>发送数据</p>
         *
         * @author hanchao 2018/4/15 20:04
         **/
        private void sendJsonDate(List<String> list) throws InterruptedException {
            //遍历
            for (int i = start; i < end; i++) {
                //每个元素都插入到DB2中 ==> 模拟数据发送到DB2
                db2.add(list.get(i));
            }
            //假定每次发送耗时1ms
            Thread.sleep(1);
        }
    }

    /**
     * <p>模拟从数据库中查询数据并形成JSON个是的数据</p>
     *
     * @author hanchao 2018/4/15 20:21
     **/
    static void queryDataToJson(List list) {
        //随机获取100万~110万个数据
        int count = RandomUtils.nextInt(1000000, 1100000);
        for (int i = 0; i < count; i++) {
            list.add("{\"id\":\"" + UUID.randomUUID() + "\"}");
        }
    }
}

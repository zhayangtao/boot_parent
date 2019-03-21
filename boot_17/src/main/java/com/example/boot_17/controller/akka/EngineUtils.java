package com.example.boot_17.controller.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/21
 * 场景说明：
 *
 * 通过多个搜索引擎查询某个条件，返回最快的查询结果。
 *
 * 定义一个消息类，用于存放搜索条件。
 * 定义一个消息类，用于存放搜索结果。
 * 定义一个角色类，用于接收搜索条件、搜索和返回搜索结果。
 * 定义一个角色类，用于派发搜索任务和接收搜索结果
 */
public class EngineUtils {
    private final static Logger LOGGER = LoggerFactory.getLogger(EngineUtils .class);
    // 搜索引擎列表
    private static List<String> engineList;

    static {
        engineList = new ArrayList<>();
        engineList.add("百度");
        engineList.add("Google");
        engineList.add("必应");
        engineList.add("搜狗");
        engineList.add("Redis");
        engineList.add("Solr");
    }

    /**
     * 模拟一个搜索迎亲进行一次问题查询
     */
    private static String searchByEngine(String question, String engine) throws InterruptedException {
        int interval = RandomUtils.nextInt(1, 5000);
        // 线程休眠，模拟搜索引擎用时
        Thread.sleep(interval);
        return "通过搜索引擎[" + engine + "]，首先查到关于(" + question + ")问题的结果，用时 = " + interval + "毫秒!";
    }

    private static List<String> getEngineList() {
        return engineList;
    }

    public static void setEngineList(List<String> engineList) {
        EngineUtils.engineList = engineList;
    }

    /**
     * 定义查询条件类，用于传递消息
     */
    static class QueryTerms {
        private String question;
        private String engine;

        String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        String getEngine() {
            return engine;
        }

        QueryTerms(String question, String engine) {
            this.question = question;
            this.engine = engine;
        }

        public void setEngine(String engine) {
            this.engine = engine;
        }
    }
    /**
     * 定义查询结果类，用于消息传递
     */
    static class QueryResult {
        private String result;

        String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        QueryResult(String result) {
            this.result = result;
        }
    }

    /**
     * 搜索引擎Actor
     */
    static class SearchEngineActor extends UntypedAbstractActor {

        @Override
        public void onReceive(Object message) throws Throwable {
            if (message instanceof QueryTerms) {
                System.out.println("接收到搜索条件：" + ((QueryTerms) message).getQuestion());
                // 通过工具类进行一次搜索引擎查询
                String result = EngineUtils.searchByEngine(((QueryTerms) message).getQuestion(), ((QueryTerms) message).getEngine());
                getSender().tell(new QueryResult(result), getSelf());
            } else {
                unhandled(message);
            }
        }
    }

    /**
     * 主角色类QuestionQuerier ，用于分发搜索任务和接收搜索结果：
     */
    static class QuestionQuerier extends UntypedAbstractActor {
        // 搜索引擎列表
        private List<String> engines;
        // 搜索结果
        private AtomicReference<String> result;
        /**
         * 问题
         */
        private String question;

        public List<String> getEngines() {
            return engines;
        }

        public void setEngines(List<String> engines) {
            this.engines = engines;
        }

        public AtomicReference<String> getResult() {
            return result;
        }

        public void setResult(AtomicReference<String> result) {
            this.result = result;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public QuestionQuerier(List<String> engines, AtomicReference<String> result, String question) {
            this.engines = engines;
            this.result = result;
            this.question = question;
        }

        /**
         * @param message 接收到的消息
         */
        @Override
        public void onReceive(Object message) {
            if (message instanceof QueryResult) {
                System.out.println("接收到搜索结果：" + ((QueryResult) message).getResult());
                // 通过 CAS 设置原子引用的值
                result.compareAndSet(null, ((QueryResult) message).getResult());
                //如果已经查询到了结果，则停止Actor
                //通过getContext()获取ActorSystem的上下文环境
                //通过getContext().stop(self())停止当前Actor
                getContext().stop(self());
            } else { // 没有收到结果，则创建搜索引擎 Actor 进行查询
                System.out.println("开始创建搜索引擎进行查询");
                // 使用原子变量去测试 Actor 的创建是否有序
                AtomicInteger atomicInteger = new AtomicInteger(1);
                engines.forEach(engine -> {
                    System.out.println("为" + engine + "创建第" + atomicInteger + "个搜索引擎Actor");
                    atomicInteger.getAndIncrement();
                    //通过actorOf(Props,name)创建Actor
                    //通过Props.create(Actor.class)创建Props
                    ActorRef actorRef = this.getContext().actorOf(Props.create(SearchEngineActor.class), "fetcher-" + engine.hashCode());
                    //创建查询条件
                    QueryTerms queryTerms = new QueryTerms(question, engine);
                    // 将查询条件告诉 actor
                    actorRef.tell(queryTerms, sender());
                });
            }
        }
    }

    /**
     * 通过多个搜索引擎查询多个条件，并返回第一条查询结果
     * @param args
     */
    public static void main(String[] args) {
        List<String> engines = EngineUtils.getEngineList();
        String result = getFirstResult("今天你吃了吗", engines);
        System.out.println(result);
    }

    private static String getFirstResult(String question, List<String> engines) {
        // 创建一个 Actor 系统
        ActorSystem actorSystem = ActorSystem.create("search-system");
        // 创建一个原子引用用于保存查询结果
        AtomicReference<String> result = new AtomicReference<>();
        // 创建 Props 对象
        Props props = Props.create(QuestionQuerier.class, engines, result, question);
        final ActorRef actorRef = actorSystem.actorOf(props, "master");
        // 告诉问题查询器开始查询
        actorRef.tell(new Object(), ActorRef.noSender());
        // 通过 while 等待 actor 进行查询
        while (result.get() == null);
        actorSystem.terminate();
        // 返回结果
        return result.get();
    }
}

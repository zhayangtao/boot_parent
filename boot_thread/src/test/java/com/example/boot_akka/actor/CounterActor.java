package com.example.boot_akka.actor;

import akka.actor.UntypedAbstractActor;
import akka.dispatch.Mapper;
import scala.concurrent.Future;

import java.util.stream.IntStream;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/11
 */
public class CounterActor extends UntypedAbstractActor {
    private Mapper<Integer, Integer> addMapper = new Mapper<Integer, Integer>() {
        @Override
        public Integer apply(Integer parameter) {
            return ++parameter;
        }
    };
    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Integer) {
            IntStream.range(0, 10000).forEach(value -> {
                Future<Integer> future = AgentDemo.counterAgent.alter(addMapper);
                AgentDemo.futures.add(future);
            });
            getContext().stop(getSelf());
        } else {
            unhandled(message);
        }
    }
}

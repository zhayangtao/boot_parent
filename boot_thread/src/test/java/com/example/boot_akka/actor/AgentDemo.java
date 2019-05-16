package com.example.boot_akka.actor;

import akka.actor.*;
import akka.agent.Agent;
import akka.dispatch.ExecutionContexts;
import akka.dispatch.Futures;
import akka.dispatch.OnComplete;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/11
 */
public class AgentDemo {

    public static Agent<Integer> counterAgent = Agent.create(0, ExecutionContexts.global());

    static ConcurrentLinkedQueue<Future<Integer>> futures = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) throws TimeoutException {
        final ActorSystem system = ActorSystem.create("agentDemo", ConfigFactory.load());
        ActorRef[] counter = new ActorRef[10];

        IntStream.range(0, counter.length).forEach(value -> {
            counter[value] = system.actorOf(Props.create(CounterActor.class), "Counter_" + value);
        });

        final Inbox inbox = Inbox.create(system);
        IntStream.range(0, counter.length).forEach(value -> {
            inbox.send(counter[value], 1);
            inbox.watch(counter[value]);
        });

        int closeCount = 0;
        while (true) {
            Object msg = inbox.receive(Duration.create(1, TimeUnit.SECONDS));
            if (msg instanceof Terminated) {
                closeCount++;
                if (closeCount == counter.length) {
                    break;
                }
            } else {
                System.out.println(msg);
            }
        }

        Futures.sequence(futures, system.dispatcher()).onComplete(
                new OnComplete<Iterable<Integer>>() {
                    @Override
                    public void onComplete(Throwable failure, Iterable<Integer> success) throws Throwable {
                        System.out.println("counterAgent=" + counterAgent.get());
                        system.terminate();
                    }
                }, system.dispatcher());


    }
}

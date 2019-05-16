package com.example.boot_akka.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

import static akka.pattern.Patterns.ask;
import static akka.pattern.Patterns.pipe;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/10
 */
public class AskMain {
    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("askDemo", ConfigFactory.load("sampleHello.conf"));
        ActorRef worker = system.actorOf(Props.create(MyWorker.class), "worker");
        ActorRef printer = system.actorOf(Props.create(MyWorker.class), "printer");
        system.actorOf(Props.create(WatchActor.class, worker), "watcher");

        // 等待 Future 返回
        Future<Object> future = ask(worker, 5, 1500);
        int re = (int) Await.result(future, Duration.create(6, TimeUnit.SECONDS));
        System.out.println("return:" + re);

        // 直接导向其他 Actor，pipe 不会等待
        future = ask(worker, 6, 1500);
        pipe(future, system.dispatcher()).to(printer);

        worker.tell(PoisonPill.getInstance(), ActorRef.noSender());
    }
}

package com.example.boot_akka.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.util.Timeout;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/11
 * 软件事务内存
 */
public class STMDemo {
    private static ActorRef company = null;
    private static ActorRef employee = null;

    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("transactionDemo");
//        company = system.actorOf(Props.create(CompanyActor.class), "company");
//        employee = system.actorOf(Props.create(EmployeeActor.class), "company");

        Timeout timeout = new Timeout(1, TimeUnit.SECONDS);

        IntStream.range(0, 20).forEach(value -> {
//            company.tell();
        });
    }
}

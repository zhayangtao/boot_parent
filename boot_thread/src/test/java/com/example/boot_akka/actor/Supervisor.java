package com.example.boot_akka.actor;

import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedAbstractActor;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/10
 * Actor 监督
 */
public class Supervisor extends UntypedAbstractActor {
    private static SupervisorStrategy strategy = new OneForOneStrategy(3,
            Duration.create(1, TimeUnit.MINUTES), param -> {
        if (param instanceof ArithmeticException) {
            System.out.println("meet ArithmeticException, just resume");
            return SupervisorStrategy.resume();
        } else if (param instanceof NullPointerException) {
            System.out.println("meet NullPointerException, restart");
            return SupervisorStrategy.restart();
        } else if (param instanceof IllegalArgumentException) {
            return SupervisorStrategy.stop();
        } else {
            return SupervisorStrategy.escalate();
        }
    });

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Props) {
            getContext().actorOf((Props) message, "restartActor");
        } else {
            unhandled(message);
        }
    }
}

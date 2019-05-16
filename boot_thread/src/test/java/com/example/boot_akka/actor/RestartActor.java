package com.example.boot_akka.actor;

import akka.actor.*;
import com.typesafe.config.ConfigFactory;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/10
 */
public class RestartActor extends UntypedAbstractActor {
    public enum Msg {
        DONE, RESTART
    }

    @Override
    public void preStart() throws Exception {
        System.out.println("preStart hashcode:" + this.hashCode());
    }

    @Override
    public void postStop() throws Exception {
        System.out.println("postStop hashcode:" + this.hashCode());
    }

    @Override
    public void postRestart(Throwable reason) throws Exception {
        super.postRestart(reason);
        System.out.println("postRestart hashcode:" + this.hashCode());
    }

    @Override
    public void preRestart(Throwable reason, Optional<Object> message) throws Exception {
        System.out.println("preRestart hashcode:" + this.hashCode());
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message == Msg.DONE) {
            getContext().stop(getSelf());
        } else if (message == Msg.RESTART) {
            System.out.println(((Object)null).toString());
            double a = 0 / 0;
        }
        unhandled(message);
    }

    private static void customStrategy(ActorSystem system) {
        ActorRef actorRef = system.actorOf(Props.create(Supervisor.class), "Supervisor");
        actorRef.tell(Props.create(RestartActor.class), ActorRef.noSender());

        ActorSelection selection = system.actorSelection("akka://lifecycle/user/Supervisor/restartActor");

        IntStream.range(0, 100).forEach(value -> {
            selection.tell(Msg.RESTART, ActorRef.noSender());
        });
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("lifecycle", ConfigFactory.load("lifecycle.conf"));
        customStrategy(system);
    }
}

package com.example.boot_akka.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import com.typesafe.config.ConfigFactory;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/09
 */
public class HelloWorld extends UntypedAbstractActor {
    private ActorRef greeter;

    @Override
    public void preStart() throws Exception {
        greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
        System.out.println("Greeter Actor Path:" + greeter.path());
        greeter.tell(Greeter.Msg.GREET, getSelf());
    }

    @Override
    public void onReceive(Object message) {
        if (message == Greeter.Msg.DONE) {
            greeter.tell(Greeter.Msg.GREET, getSelf());
            getContext().stop(getSelf());
        } else {
            unhandled(message);
        }
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("Hello", ConfigFactory.load("sampleHello.conf"));
        ActorRef actorRef = system.actorOf(Props.create(HelloWorld.class), "HelloWorld");
        System.out.println("HelloWorld Actor Path:" + actorRef.path());
    }
}

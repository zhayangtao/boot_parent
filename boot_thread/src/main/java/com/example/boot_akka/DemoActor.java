package com.example.boot_akka;

import akka.actor.AbstractActor;
import akka.actor.AbstractLoggingActor;
import akka.actor.Props;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/21
 */
public class DemoActor extends AbstractActor {
    private final Integer magicNumber;

    public DemoActor(Integer magicNumber) {
        this.magicNumber = magicNumber;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(Integer.class, integer -> {
            getSender().tell(integer + magicNumber, getSelf());
        }).build();
    }

    static Props props(Integer magicNumber) {
        return Props.create(DemoActor.class, () -> new DemoActor(magicNumber));
    }

    static class SomeOtherActor extends AbstractActor {

        @Override
        public Receive createReceive() {
            return null;
        }
    }

    static class DemoMessagesActor extends AbstractLoggingActor {

        public static class Greeting {
            private final String from;

            public Greeting(String from) {
                this.from = from;
            }

            public String getGreeter() {
                return from;
            }
        }

        @Override
        public Receive createReceive() {
            return null;
        }
    }
}

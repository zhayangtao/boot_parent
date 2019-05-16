package com.example.boot_akka.actor;

import akka.actor.UntypedAbstractActor;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/09
 */
public class Greeter extends UntypedAbstractActor {
    public static enum Msg {
        GREET, DONE;
    }
    @Override
    public void onReceive(Object message) throws Throwable {
        if (message == Msg.GREET) {
            System.out.println("Hello World!");
            getSender().tell(Msg.DONE, getSelf());
        } else {
            unhandled(message);
        }
    }
}

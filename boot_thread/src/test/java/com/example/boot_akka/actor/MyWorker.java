package com.example.boot_akka.actor;

import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/10
 */
public class MyWorker extends UntypedAbstractActor {
    private final LoggingAdapter loggingAdapter = Logging.getLogger(getContext().system(), this);
    public enum Msg {
        WORKING, DONE, CLOSE;
    }

    @Override
    public void preStart() throws Exception {
        System.out.println("MyWorker is starting");
    }

    @Override
    public void postStop() throws Exception {
        System.out.println("MyWorker is stopping");
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message == Msg.WORKING) {
            System.out.println("I am working");
        }
        if (message == Msg.DONE) {
            System.out.println("stop working");
        }
        if (message == Msg.CLOSE) {
            System.out.println("I am shutting down");
            getSender().tell(Msg.CLOSE, getSelf());
            getContext().stop(getSelf());
        } else {
            unhandled(message);
        }
    }
}

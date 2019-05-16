package com.example.boot_akka.actor;

import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/10
 */
public class BabyActor extends UntypedAbstractActor {
    private final LoggingAdapter loggingAdapter = Logging.getLogger(getContext().getSystem(), this);

    public enum Msg {
        SLEEP, PLAY, CLOSE
    }

    Procedure<Object> angry = message -> {
        System.out.println("angryApply:" + message);
        if (message == Msg.SLEEP) {
            getSender().tell("I am already angry", getSelf());
            System.out.println("I am already angry");
        } else if (message == Msg.PLAY) {
            System.out.println("I like playing");
//            getContext().become();
        }
    };

    Procedure<Object> happy = message -> {
        System.out.println("happyApply:" + message);
        if (message == Msg.PLAY) {
            getSender().tell("I am already happy :)", getSelf());
            System.out.println("I am already happy :)");
        } else if (message == Msg.SLEEP) {
            System.out.println("I don't want to sleep");
//            getContext().become(angry);
        }
    };
    @Override
    public void onReceive(Object message) throws Throwable {

    }
}

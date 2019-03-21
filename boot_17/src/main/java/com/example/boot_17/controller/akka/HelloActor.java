package com.example.boot_17.controller.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/21
 * Akka Actors的入门级的编程流程：
 *
 * 编写Message类，用于存放消息。
 * 编写Actor类，用于定义接收到消息之后所做的操作，注意日志处理。
 * 定义一个ActorSystem，用于管理和调度程序中的Actor。
 * 定义ActorRef，用于引用Actor类。
 * 调用actorRef.tell(message)，用于向此Actor发送消息。
 * 关闭ActorSystem。
 */
public class HelloActor extends UntypedAbstractActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println("HelloActor receive message:" + message);
        if (message instanceof HelloMessage) {
            System.out.println("Hello " + ((HelloMessage) message).getName() + "!");
        }
    }

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("hello-system");
        ActorRef actorRef = actorSystem.actorOf(Props.create(HelloActor.class), "hello-actor");

        actorRef.tell(new HelloMessage("World"), null);
        actorRef.tell(new HelloMessage("Akka Actor"), null);
        // 终止 ActorSystem
        actorSystem.terminate();
    }
}

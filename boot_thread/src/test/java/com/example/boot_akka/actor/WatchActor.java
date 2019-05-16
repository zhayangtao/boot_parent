package com.example.boot_akka.actor;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/10
 * 消息路由
 */
public class WatchActor extends UntypedAbstractActor {
    private final LoggingAdapter loggingAdapter = Logging.getLogger(getContext().system(), this);

    private Router router;
    {
        List<Routee> routees = new ArrayList<>();
        IntStream.range(0, 5).forEach(value -> {
            ActorRef worker = getContext().actorOf(Props.create(MyWorker.class), "worker_" + value);
            getContext().watch(worker);
            routees.add(new ActorRefRoutee(worker));
        });
        router = new Router(new RoundRobinRoutingLogic(), routees);
    }

    WatchActor(ActorRef actorRef) {
        getContext().watch(actorRef);
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof MyWorker.Msg) {
            router.route(message, getSender());
        } else if (message instanceof Terminated) {
            router = router.removeRoutee(((Terminated) message).actor());
            System.out.println(((Terminated) message).actor().path()
                    + " is clodes, routees=" + router.routees().size());
            if (router.routees().size() == 0) {
                System.out.println("Close system");
//                RouteMain.flag.send(false);
                getContext().system().terminate();
            }
        } else unhandled(message);
    }
}

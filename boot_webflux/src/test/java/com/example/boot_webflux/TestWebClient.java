package com.example.boot_webflux;

import com.example.boot_webflux.entity.MyEvent;
import com.example.boot_webflux.entity.User;
import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/26
 */
public class TestWebClient {

    @Test
    public void webClientTest() throws InterruptedException {
        WebClient webClient = WebClient.create("http://localhost:8083");
        Flux<MyEvent> resp = webClient.get().uri("/events")
                .retrieve() // 异步
                .bodyToFlux(MyEvent.class);
        resp.subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    public void webClientTest2() {
        Flux<MyEvent> eventFlux = Flux.interval(Duration.ofSeconds(1))
                .map(aLong -> new MyEvent(System.currentTimeMillis(), "message-" + aLong))
                .take(5);
        WebClient webClient = WebClient.create("http://localhost:8083");
        webClient.post().uri("/events")
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(eventFlux, MyEvent.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    @Test
    public void webClientTest3() {
        WebClient webClient = WebClient.create("http://localhost:8083");
        webClient.get().uri("/user")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .exchange() // 异步接受
                .flatMapMany(clientResponse -> clientResponse.bodyToFlux(User.class))
                .doOnNext(System.out::println)
                .blockLast(); // 在接收到最后一个元素前阻塞
    }

    @Test
    public void webClientTest4() {
        WebClient webClient = WebClient.create("http://localhost:8083");
        webClient.get().uri("/times")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(Stream.class)
                .log()
                .take(10)
                .blockLast();
    }

    @Test
    public void webClientTest5() {
        Flux<MyEvent> eventFlux = Flux.interval(Duration.ofSeconds(1))
                .map(aLong -> new MyEvent(System.currentTimeMillis(), "message-" + 1))
                .take(10); // 限制为5个元素
        WebClient webClient = WebClient.create("http://localhost:8083");
        webClient.post().uri("/events")
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(eventFlux, MyEvent.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    @Test
    public void webClientTest6() {
        WebClient webClient = WebClient.create("http://localhost:8083");
        webClient.get().uri("/events")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .exchange() // 异步接受
                .flatMapMany(clientResponse -> clientResponse.bodyToFlux(MyEvent.class))
                .doOnNext(System.out::println)
                .blockLast(); // 在接收到最后一个元素前阻塞
    }

    @Test
    public void fluxArrayTest() {
        Flux.range(1, 6).subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("onSubscribe");
                subscription.request(10);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext:" + integer);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }
}

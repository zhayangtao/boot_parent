package com.example.boot_webflux;

import com.example.boot_webflux.entity.MyEvent;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/26
 */
public class TestWebClient {

    @Test
    public void webClientTest() throws InterruptedException {
        WebClient webClient = WebClient.create("http://localhost:8083");
        Mono<String> resp = webClient.get().uri("/hello")
                .retrieve()
                .bodyToMono(String.class);
        resp.subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    public void webClientTest2() {
        Flux<MyEvent> eventFlux = Flux.interval(Duration.ofSeconds(1))
                .map(aLong -> new MyEvent(System.currentTimeMillis(), "message-" + aLong))
                .take(5);
        WebClient webClient = WebClient.create("http://localhost:8083");
        webClient.post().uri("events")
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(eventFlux, MyEvent.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}

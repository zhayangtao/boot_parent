package com.example.boot_webflux;

import org.junit.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/25
 */
public class ReactorTest {

    private Flux<Integer> getFlux() {
        return Flux.just(1, 2, 3, 4, 5, 6);
    }

    private Mono<Integer> getMonoError() {
        return Mono.error(new Exception("mono error"));
    }

    private Flux<String> getZipFlux() {
        String desc = "Zip two sources together, that is to say wait for all the sources to emit one element and combine these elements once into a Tuple2.";
        return Flux.fromArray(desc.split("\\s+"));
    }

    @Test
    public void testReactor() {
        Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6};
        Flux.fromArray(array);
        List<Integer> list = Arrays.asList(array);
        Flux.fromIterable(list);
        Stream<Integer> stream = list.stream();
        Flux.fromStream(stream);

        // 只有完成信号的空数据流
        Flux.just();
        Flux.empty();
        Mono.empty();
        Mono.justOrEmpty(Optional.empty());
        // 只有错误信号的数据流
        Flux.error(new Exception("some error"));
        Mono.error(new Exception("badbadbad"));

        // 订阅数据流
        Flux.just(1, 2, 3, 4, 5, 6).subscribe(System.out::print);
        System.out.println();
        Mono.just(1).subscribe(System.out::println);
    }

    /**
     * 单元测试工具 StepVerifier
     */
    @Test
    public void testStepVerifier() {
        StepVerifier.create(getFlux())
                .expectNext(1, 2, 3, 4, 5, 6)
                .expectComplete()
                .verify();
        StepVerifier.create(getMonoError())
                .expectErrorMessage("mono error")
                .verify();
        StepVerifier.create(Flux.range(1, 6).map(integer -> integer * integer))
                .expectNext(1, 4, 9, 16, 25, 37)
                .expectComplete();
        StepVerifier.create(Flux.just("flux", "mono").flatMap(s -> Flux.fromArray(s.split("\\s*"))
                .delayElements(Duration.ofMillis(100)))
                .doOnNext(System.out::print))
                .expectNextCount(8)
                .verifyComplete();
        StepVerifier.create(Flux.range(1, 6).filter(integer -> integer % 2 == 1).map(integer -> integer * integer))
                .expectNext(1, 9, 25)
                .verifyComplete();
    }

    @Test
    public void testSimpleOperation() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Flux.zip(getZipFlux(), Flux.interval(Duration.ofMillis(200)))
                .subscribe(objects -> System.out.println(objects.getT1()), null, latch::countDown);
        latch.await(10, TimeUnit.SECONDS);
    }

    @Test
    public void testErrorHandling() throws InterruptedException {
        Flux.range(1, 6).map(integer -> 10 / (integer - 3))
                .map(i -> i * i)
                .subscribe(System.out::println, System.out::println);
        // 捕获异常并返回一个静态缺省值
        Flux.range(1, 6).map(i -> 10 / (i - 3))
                .onErrorReturn(0)
                .map(i -> i * i)
                .subscribe(System.out::println, System.out::println);
        // 捕获并执行一个异常处理方法或计算一个候补
        Flux.range(1, 6).map(i -> 10 / (i - 3))
                .onErrorResume(e -> Mono.just(new Random().nextInt(6)))
                .map(i -> i * i)
                .subscribe(System.out::println, System.out::println);
        // 捕获并抛出其他异常
        Flux.just("timeout")
                .flatMap(k -> Flux.fromArray(k.split("")))
                .onErrorMap(throwable -> new RuntimeException("another exception", throwable));
        // 捕获，记录日志后抛出
        Flux.just("timeout")
                .flatMap(k -> Flux.fromArray(k.split("")))
                .doOnError(throwable -> {
                    // 记录日志
                });
        // 使用 finally 清理资源，或使用 try-with-resource

        // retry
        Flux.range(1, 6).map(i -> 10 / (3 - i))
                .retry(1)
                .subscribe(System.out::println, System.out::println);
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /**
     * 测试回压
     */
    @Test
    public void testBackPressure() {
        Flux.range(1, 6)
                .doOnRequest(n -> System.out.println("request " + n + " values..."))
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        System.out.println("Subscribed and make a request...");
                        request(1);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("get value [" + value + "]");
                        request(1);
                    }
                });
    }

    /**
     * 测试 map 和 flatMap 的区别
     * map会将一个元素变成一个新的Stream
     * 但是flatMap会将结果打平，得到一个单个元素
     */
    @Test
    public void testMapFlatMap() {
        List<String> list = Arrays.asList("hello welcome", "world hello", "hello world",
                "hello world welcome");
        // map 和 flatMap 的区别
        list.stream().map(s -> Arrays.stream(s.split(" ")))
                .distinct().collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("---------");
        list.stream().flatMap(s -> Arrays.stream(s.split(" ")))
                .distinct().collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("---------------");
        List<Stream<String>> listResult = list.stream().map(s -> Arrays.stream(s.split(" "))).distinct().collect(Collectors.toList());
        List<String> listResult2 = list.stream().flatMap(s -> Arrays.stream(s.split(" "))).distinct().collect(Collectors.toList());
        // 可以这样使用
        list.stream().map(item -> item.split(" ")).flatMap(Arrays::stream)
                .distinct().collect(Collectors.toList()).forEach(System.out::println);
    }
}

package com.example.boot_webflux;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/05/09
 */
public class CustomFlux {

    @Test
    public void testGenerate() {
        final AtomicInteger count = new AtomicInteger(1);
        Flux.generate(synchronousSink -> {
            synchronousSink.next(count.get() + " : " + new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count.getAndIncrement() >= 5) {
                synchronousSink.complete();
            }
        }).subscribe(System.out::println);

    }

    // 增加一个伴随状态
    @Test
    public void testGenerate2() {
        Flux.generate(() -> 1, (integer, synchronousSink) -> {
            synchronousSink.next(integer + " : " + new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (integer >= 5) {
                synchronousSink.complete();
            }
            return integer + 1;
        }).subscribe(System.out::println);
    }

    @Test
    public void studentCompareTest() {
        @Data
        @AllArgsConstructor
        class Student {
            private int id;
            private String name;
            private double height;
            private double score;
        }

        List<Student> students = new ArrayList<>();
        students.add(new Student(10001, "张三", 1.73, 88));
        students.add(new Student(10002, "李四", 1.71, 96));
        students.add(new Student(10003, "王五", 1.85, 88));

        class StudentIdComparator<S extends Student> implements Comparator<S> {

            @Override
            public int compare(S s1, S s2) {
                return Integer.compare(s1.getId(), s2.getId());
            }
        }
        students.sort(new StudentIdComparator<>());
        System.out.println(students);

        // lambda
        students.sort(Comparator.comparingDouble(Student::getHeight));
    }

    @Test
    public void testReactor() {
        Integer[] integers = new Integer[]{1, 2, 3, 4, 5};
        Flux<Integer> flux = Flux.fromArray(integers);
        List<Integer> list = Arrays.asList(integers);
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
        Mono.error(new Exception("some errro"));

        // 订阅前什么都不会发生
        Flux.just(1, 2, 3, 4, 5).subscribe(System.out::print);
        System.out.println();
        Mono.just(1).subscribe(System.out::println);

        Flux.just(1, 2, 3, 4, 5).subscribe(System.out::println, System.out::println, () -> System.out.println("completed"));
        Mono.error(new Exception("some error")).subscribe(System.out::println, System.out::println, () -> System.out.println("completed"));
    }

    private Flux<Integer> generate() {
        return Flux.just(1, 2, 3, 4, 5, 6);
    }

    private Mono<Integer> generateMono() {
        return Mono.error(new Exception("some error"));
    }

    @Test
    public void testViaStepVerifier() {
        StepVerifier.create(generate())
                .expectNext(1, 2, 3, 4, 5, 6)
                .expectComplete()
                .verify();
        StepVerifier.create(generateMono())
                .expectErrorMessage("some error")
                .verify();

        StepVerifier.create(Flux.range(1, 6).map(integer -> integer ^ 2))
                .expectNext(1, 4, 9, 16, 25, 36)
                .expectComplete();
        StepVerifier.create(Flux.just("flux", "mono")
                .flatMap(s -> Flux.fromArray(s.split("\\s*")) // 拆分为包含一个字符的字符串流
                        .delayElements(Duration.ofMillis(100))) // 每个元素延迟 100 ms
                .doOnNext(System.out::print)) // 打印每个元素
                .expectNextCount(8) // 验证是否发出了8个元素
                .verifyComplete();
        // filter
        StepVerifier.create(Flux.range(1, 6)
                .filter(integer -> integer % 2 == 1)
                .map(integer -> integer * integer))
                .expectNext(1, 9, 25)
                .verifyComplete();
        Flux.range(1, 6)
                .map(i -> 10 / (i - 3))
                .onErrorReturn(0)
                .map(i -> i * i)
                .subscribe(System.out::println, System.out::println);
        Flux.just("timeout1")
                .flatMap(s -> System.out::println)
                .onErrorResume(throwable -> Flux.error(new RuntimeException("sla", throwable)));
        // 捕获，记录错误日志，然后继续抛出
        Flux.just(1, 2)
                .flatMap(k -> System.out::println)
                .doOnError(e -> System.out.println("记录日志")) // 只读
                .onErrorResume(e -> Flux.error(new RuntimeException("sla", e)));

        Flux.just("for", "bar")
                .doFinally(type -> {
                    if (type == SignalType.CANCEL) {
                        // do something
                    }
                }).take(1);
        // 重试
        Flux.range(1, 6)
                .map(i -> 10 / (3 - i))
                .retry(1)
                .subscribe(System.out::println, System.out::println);
    }


    private Flux<String> getZipFlux() {
        String desc = "Zip two sources together, that is to say wait for all the sources to emit one element and combine these elements once into a Tuple2.";
        return Flux.fromArray(desc.split("\\s+"));
    }

    @Test
    public void testSimpleOperators() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Flux.zip(getZipFlux(), Flux.interval(Duration.ofMillis(200)))
                .subscribe(objects -> System.out.println(objects.getT1()), null, latch::countDown);
        latch.await(10, TimeUnit.SECONDS);
    }

    @Test
    public void testSchedulers() {
        System.out.println(Schedulers.immediate());
    }

    /**
     * 同步阻塞方法
     * @return
     */
    private String getStringSync() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello, Reactor";
    }

    @Test
    public void testSyncToAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Mono.fromCallable(this::getStringSync)
                .subscribeOn(Schedulers.elastic()) // 将任务调度到 Schedulers 内置的弹性线程池执行
                .subscribe(System.out::println, null, latch::countDown);
        latch.await(10, TimeUnit.SECONDS);
    }

    // 回压
    @Test
    public void testBackPressure() {
        Flux.range(1, 6)
                .doOnRequest(n -> System.out.println("request " + n + " values..."))
                .subscribe(new BaseSubscriber<Integer>() {
//                    在订阅的时候执行的操作
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        System.out.println("subscribed and make a request...");
                        // 订阅时向上游请求一个元素
                        request(1); // 通过request(n)的方法来告知上游它的需求速度
                    }
//                  每次在收到一个元素的时候的操作
                    @Override
                    protected void hookOnNext(Integer value) {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Get value [" + value + "]");
                        request(1); // 通过request(n)的方法来告知上游它的需求速度
                    }
                });
    }
}

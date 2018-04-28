package com.example.boot_rxjava;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/27
 */
public class TestRxJava {

    public void test() {
        Observable.create(subscriber -> { // 第一步：初始化 Observable
            System.out.println(1);
            subscriber.onNext(1);
            System.out.println(2);
            subscriber.onNext(2);
            System.out.println(3);
            subscriber.onNext(3);
            System.out.println(4);
            subscriber.onNext(4);
        }).subscribe(new Observer<Object>() { // 第三步：订阅

            // 第二步：初始化 Observer
            private int i;
            private Disposable disposable;

            @Override
            public void onSubscribe(Disposable disposable) {
                this.disposable = disposable;
            }

            @Override
            public void onNext(Object o) {
                this.i++;
                if (i == 2) {
                    this.disposable.dispose();
                }
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });

//        Observable.create(subscriber -> {
//            subscriber.onNext(1);
//            subscriber.onComplete();
//        }).subscribeOn(Schedulers.newThread()).subscribeOn(Schedulers.io())
//        .observeOn();
    }

}

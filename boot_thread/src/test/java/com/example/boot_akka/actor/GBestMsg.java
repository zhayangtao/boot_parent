package com.example.boot_akka.actor;

import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/11
 * akka 实现 粒子群算法
 */
public final class GBestMsg {
    final PsoValue value;

    public GBestMsg(PsoValue value) {
        this.value = value;
    }

    public PsoValue getValue() {
        return value;
    }
}
final class PBestMsg {
    final PsoValue value;

    public PBestMsg(PsoValue value) {
        this.value = value;
    }

    public PsoValue getValue() {
        return value;
    }
}
final class PsoValue {
    final double value;
    final List<Double> x;

    public PsoValue(double value, List<Double> x) {
        this.value = value;
        this.x = x;
    }

    public double getValue() {
        return value;
    }

    public List<Double> getX() {
        return x;
    }

    @Override
    public String toString() {
        return "PsoValue{" +
                "value=" + value +
                ", x=" + x +
                '}';
    }
}

/**
 * 适应度
 */
class Fitness {
    public static double fitness(List<Double> x) {
        double sum = 0;
        for (Double x1 : x) {
            sum += Math.sqrt(x1);
        }
        return sum;
    }


    public static void main(String[] args) {
        long sum = 0;
        long start = System.currentTimeMillis();
        sum = IntStream.range(0, 100000000).parallel().sum();
        long end = System.currentTimeMillis();
        System.out.println(end - start); // 88

        start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            sum += i;
        }
        end = System.currentTimeMillis();
        System.out.println(end - start); // 28

    }
}

/**
 * 粒子群
 */
class Bird extends UntypedAbstractActor {
    private final LoggingAdapter loggingAdapter = Logging.getLogger(getContext().getSystem(), this);
    private PsoValue pBest = null;
    private PsoValue gBest = null;
    private List<Double> velocity = new ArrayList<>(5);
    private List<Double> x = new ArrayList<>(5);
    private Random random = new Random();

    @Override
    public void preStart() throws Exception {
        for (int i = 0; i < 5; i++) {
            velocity.add(Double.NEGATIVE_INFINITY);
            x.add(Double.NEGATIVE_INFINITY);
        }

        // x1 <= 400
        x.set(1, (double) random.nextInt(401));

    }

    @Override
    public void onReceive(Object message) throws Throwable {

    }
}
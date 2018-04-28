package com.example.boot_webflux.functions;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/27
 */
public class Car {

    private static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    private static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }

    private static void coll() {
        System.out.println("coll");
    }

    private void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }

    private void repair() {
        System.out.println("Repaired " + this.toString());
    }

    public static void main(String[] args) {
        // 构造器引用
        final Car car = Car.create(Car::new);
        final List<Car> carList = Collections.singletonList(car);

        // 静态方法引用
        carList.forEach(Car::collide);
        carList.forEach(car1 -> Car.collide(car1));

        // 实例方法引用
        carList.forEach(Car::repair);
        carList.forEach(car1 -> car1.repair());

        // 带参实例方法
        carList.forEach(car1 -> car1.follow(car1));
        // 等于
        final Car car1 = Car.create(Car::new);
        carList.forEach(car1::follow);

//        carList.forEach(car2 -> car2::coll); // 不支持
//        carList.forEach(Car::coll); // 不支持
    }
}

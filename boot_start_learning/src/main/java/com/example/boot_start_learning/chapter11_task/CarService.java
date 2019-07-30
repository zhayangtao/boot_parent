package com.example.boot_start_learning.chapter11_task;

import java.util.List;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/30
 */
public interface CarService {
    List<Car> findAll();

    Car save(Car car);

    void updateCarAgeJob();

    boolean isDone();
}

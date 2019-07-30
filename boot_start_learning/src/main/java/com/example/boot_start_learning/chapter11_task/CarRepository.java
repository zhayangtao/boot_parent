package com.example.boot_start_learning.chapter11_task;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/30
 */
public interface CarRepository extends JpaRepository<Car, Long> {
}

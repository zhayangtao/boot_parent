package com.example.boot_start_learning.chapter12_remote;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/30
 */
public interface SingerRepository extends JpaRepository<Singer, Long> {
    List<Singer> findByFirstName(String firstName);
}

package com.example.boot_start_learning.chapter16;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author no one
 * @version 1.0
 * @since 2019/08/02
 */
public interface SingerRepository extends JpaRepository<Singer, Long> {
}

package com.example.boot_start_learning.chapter12_remote;

import java.util.List;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/30
 */
public interface SingerService {
    List<Singer> findAll();

    List<Singer> findByFirstName(String firstName);

    Singer findById(Long id);

    Singer save(Singer singer);

    void delete(Singer singer);
}

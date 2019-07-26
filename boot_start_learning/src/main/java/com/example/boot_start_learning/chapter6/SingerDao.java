package com.example.boot_start_learning.chapter6;

import java.util.List;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/19
 */
public interface SingerDao {
    List<Singer> findAll();

    List<Singer> findByFirstName(String firstName);

    String findLastNameById(Long id);

    String findFirstNameById(Long id);

    void insert(Singer singer);

    void update(Singer singer);

    void delete(Long singerId);

    List<Singer> findAllWithDetail();

    void insertWithDetail(Singer singer);
}

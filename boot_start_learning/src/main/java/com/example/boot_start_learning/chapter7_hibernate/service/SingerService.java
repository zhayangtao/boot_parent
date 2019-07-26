package com.example.boot_start_learning.chapter7_hibernate.service;

import com.example.boot_start_learning.chapter7_hibernate.entities.Singer;

import java.util.List;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/25
 */
public interface SingerService {
    List<Singer> findAll();

    List<Singer> findAllWithAlbum();

    Singer findById(Long id);

    Singer save(Singer contact);

    void delete(Singer contact);

    List<Singer> findAllByNativeQuery();
}

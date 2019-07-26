package com.example.boot_start_learning.chapter7_hibernate.dao;

import com.example.boot_start_learning.chapter7_hibernate.entities.Singer;

import java.util.List;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/24
 */
public interface SingerDao {
    List<Singer> findAll();

    List<Singer> findAllWithAlbum();

    Singer findById(Long id);

    Singer save(Singer contact);

    void delete(Singer contact);

}

package com.example.boot_start_learning.chapter8_jpa;

import com.example.boot_start_learning.chapter8_jpa.entities.Album;
import com.example.boot_start_learning.chapter8_jpa.entities.Singer;

import java.util.List;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/26
 */
public interface AlbumService {
    List<Album> findBySinger(Singer singer);

    List<Album> findByTitle(String title);
}

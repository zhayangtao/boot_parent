package com.example.boot_start_learning.chapter8_jpa;

import com.example.boot_start_learning.chapter8_jpa.entities.Album;
import com.example.boot_start_learning.chapter8_jpa.entities.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/26
 */
public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findBySinger(Singer singer);

    @Query("select a from Album a where a.title like %:title%")
    List<Album> findByTitle(@Param("title") String title);
}

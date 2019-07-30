package com.example.boot_start_learning.chapter8_jpa;

import com.example.boot_start_learning.chapter8_jpa.entities.Album;
import com.example.boot_start_learning.chapter8_jpa.entities.Singer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/26
 */
@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public List<Album> findBySinger(Singer singer) {
        return albumRepository.findBySinger(singer);
    }

    @Override
    public List<Album> findByTitle(String title) {
        return albumRepository.findByTitle(title);
    }
}

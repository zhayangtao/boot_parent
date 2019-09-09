package com.example.boot_start_learning.chapter16;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author no one
 * @version 1.0
 * @since 2019/08/02
 */
public interface SingerService {
    List<Singer> findAll();

    Singer findById(Long id);

    Singer save(Singer singer);

    Page<Singer> findAllByPage(Pageable pageable);
}

package com.example.boot_13_redis.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.Optional;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/06
 */
public interface UserService {
    Page<User> queryPage(int page, int size, Sort id);

    User findByName(String name);

    Optional<User> findById(Integer id);

    void save(User user);
}

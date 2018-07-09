package com.example.boot_13_redis.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/06
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "from User where name = ?1")
    User findByName(String name);

    User findByNameLike(String name);

    User findByNameEndingWith(String name);

    User findByNameStartingWithOrderByNameAsc(String name);

    @Query(value = "from User where id=:id")
    Optional<User> findById(@Param("id") Integer id);
}

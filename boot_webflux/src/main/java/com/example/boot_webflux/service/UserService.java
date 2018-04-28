package com.example.boot_webflux.service;

import com.example.boot_webflux.entity.User;
import com.example.boot_webflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/26
 * 用户服务
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * 保存或者更新
     *
     * @param user
     * @return
     */
    public Mono<User> save(User user) {
        return userRepository.save(user)
                .onErrorResume(e -> userRepository.findByUsername(user.getUsername())
                        .flatMap(user1 -> {
                            user1.setId(user1.getId());
                            return userRepository.save(user);
                        }));
    }

    /**
     * 删除用户
     *
     * @param username
     * @return
     */
    public Mono<Long> deleteByUsername(String username) {
        return userRepository.deleteByUsername(username);
    }

    public Mono<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

}

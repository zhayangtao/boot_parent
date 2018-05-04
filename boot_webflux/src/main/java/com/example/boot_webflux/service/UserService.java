package com.example.boot_webflux.service;

import com.example.boot_webflux.entity.User;
import com.example.boot_webflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

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

    public void test() {
        int[] intArr;
        intArr = new int[]{1, 2, 3, 4, 5};
        int[] intArr2 = {1, 2, 3, 4, 5};
        int[] intArr3 = new int[5];
        intArr3[0] = 1;
    }

    public static void main(String[] args) {
        int[][] a = new int[4][];
        for (int[] anA : a) {
            System.out.println(Arrays.toString(anA));
        }

        int[] arr1 = new int[]{3, -4, 25, 16, 30, 18};
        Arrays.parallelSort(arr1);
        System.out.println(Arrays.toString(arr1));
    }
}

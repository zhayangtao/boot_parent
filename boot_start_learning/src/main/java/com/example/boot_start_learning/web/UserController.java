package com.example.boot_start_learning.web;

import com.example.boot_start_learning.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/06/04
 */
@RestController
@RequestMapping("/users")
public class UserController {
    static Map<Long, User> users = new ConcurrentHashMap<>();

    @GetMapping("/")
    public List<User> getUserList() {
        return new ArrayList<>(users.values());
    }

    @PostMapping("/")
    public String postUser(@ModelAttribute User user) {
        users.put(user.getId(), user);
        return "success";
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return users.get(id);
    }

    @PutMapping("/{id}")
    public String putUser(@PathVariable Long id, @ModelAttribute User user) {
        User user1 = users.get(id);
        return "success";
    }
}

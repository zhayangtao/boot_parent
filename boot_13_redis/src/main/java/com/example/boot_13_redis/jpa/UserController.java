package com.example.boot_13_redis.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/06
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getPage")
    public void getPage() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Page<User> page = userService.queryPage(1, 2, sort);
        System.out.println("当前总记录数"+page.getTotalElements());
        System.out.println("当前页面的记录数"+page.getNumberOfElements());
        System.out.println("总页数"+page.getTotalPages());
        System.out.println("当前页面的List"+page.getContent());
        System.out.println("当前页码"+page.getNumber());
    }

    @GetMapping(value = "/getUser/{name}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public User getUser(@PathVariable("name") String name) {
        return userService.findByName(name);
    }

    @GetMapping(value = "/getUser/{id}")
    public Optional<User> getUserById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @PostMapping("/saveUser")
    public void saveUser(User user) {
        userService.save(user);
    }
}

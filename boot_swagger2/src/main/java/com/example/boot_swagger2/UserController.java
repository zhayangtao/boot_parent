package com.example.boot_swagger2;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/05/08
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户列表", notes = "用户列表")
    @GetMapping(value = "")
    public List<Void> getUsers() {
        return null;
    }

    @ApiOperation(value = "创建用户", notes = "创建用户")
    @PostMapping(value = "")
    public String postUser(@RequestBody int userId) {
        return "postUser";
    }

}

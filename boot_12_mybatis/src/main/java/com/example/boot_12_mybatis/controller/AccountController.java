package com.example.boot_12_mybatis.controller;

import com.example.boot_12_mybatis.dto.Account;
import com.example.boot_12_mybatis.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/1/25
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/list")
    public List<Account> getAccounts() {
        return accountService.findAccountList();
    }

    @GetMapping(value = "/{id}")
    public Account getAccountById(@PathVariable("id") int id) {
        return accountService.findAccountById(id);
    }

    @PutMapping(value = "/{id}")
    public String updateAccount(@PathVariable("id") int id, @RequestParam(value = "name", required = true) String name,
                                @RequestParam(value = "money", required = true) double money) {
        int t = accountService.updateAccount(name, money, id);
        if (t == 1) {
            return "success";
        } else {
            return "fail";
        }

    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable(value = "id") int id) {
        int t = accountService.delete(id);
        if (t == 1) {
            return "success";
        } else {
            return "fail";
        }

    }

    @PostMapping(value = "")
    public String postAccount(@RequestParam(value = "name") String name,
                              @RequestParam(value = "money") double money) {

        int t = accountService.addAccount(name, money);
        if (t == 1) {
            return "success";
        } else {
            return "fail";
        }

    }
}

package com.example.boot_12_mybatis.service;

import com.example.boot_12_mybatis.dao.AccountMapper;
import com.example.boot_12_mybatis.dto.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/1/25
 */
@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    public int addAccount(String name, double money) {
        return accountMapper.addAccount(name, money);
    }

    public int updateAccount(String name, double money, int id) {
        return accountMapper.updateAccount(name, money, id);
    }

    public int delete(int id) {
        return accountMapper.deleteAccount(id);
    }

    public Account findAccountById(int id) {
        return accountMapper.findAccountById(id);
    }

    public List<Account> findAccountList() {
        return accountMapper.findAccountList();
    }
}

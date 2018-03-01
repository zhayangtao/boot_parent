package com.example.boot_12_mybatis.dao;

import com.example.boot_12_mybatis.dto.Account;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/1/25
 */
@Mapper
public interface AccountMapper {

    @Insert("INSERT INTO account(NAME, money) VALUES(#{name}, #{money})")
    int addAccount(@Param("name") String name, @Param("money") double money);

    @Update("udpate account set name=#{name}, money=#{money} where id=#{id}")
    int updateAccount(@Param("name") String name, @Param("money") double money, @Param("id") int id);

    @Delete("delete from account where id=#{id}")
    int deleteAccount(int id);

    @Select("select id, name, money from account where id=#{id}")
    Account findAccountById(@Param("id") int id);

    @Select("select id, name, money from account")
    List<Account> findAccountList();
}

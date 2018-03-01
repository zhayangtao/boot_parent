package com.example.boot.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2017/12/28
 */
public class User implements Serializable{
    private static final long serialVersionUID = 7939646839716049963L;

    private Long id;
    private String userName;
    private String password;
    private String email;
    private String nickname;
    private String regTime;

    public User() {
    }

    public User(String userName, String password, String email, String nickname, String regTime) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.regTime = regTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("userName", userName)
                .append("password", password)
                .append("email", email)
                .append("nickname", nickname)
                .append("regTime", regTime)
                .toString();
    }
}

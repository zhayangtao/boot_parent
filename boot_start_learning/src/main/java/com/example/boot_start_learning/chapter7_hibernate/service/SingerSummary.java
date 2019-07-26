package com.example.boot_start_learning.chapter7_hibernate.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/25
 * 使用构造函数表达式查询自定义结果类型
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SingerSummary implements Serializable {
    private static final long serialVersionUID = 913639364992958269L;
    private String firstName;
    private String lastName;
    private String latestAlbum;
}

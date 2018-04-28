package com.example.boot_webflux.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {

    @Id
    private String id; // 注解属性 id 为 ID
    @Indexed(unique = true) // 注解属性 username 为索引，且不能重复
    private String username;
    private String name;
    private String phone;
    private Date birthday;

}

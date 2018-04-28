package com.example.boot_webflux.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "event")
public class MyEvent {

    @Id
    private Long id;
    private String message;
}

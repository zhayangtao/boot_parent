package com.example.boot_groovy

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


/**
 * @author zhayangtao* @version 1.0* @since 2019/01/30
 */
@Entity
class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String reader;
    String isbn;
    String title;
    String author;
    String description;
}

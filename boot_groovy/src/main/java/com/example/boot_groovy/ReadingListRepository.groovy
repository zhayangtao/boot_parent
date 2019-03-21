package com.example.boot_groovy


/**
 * @author zhayangtao* @version 1.0* @since 2019/01/30
 */
interface ReadingListRepository {
    List<Book> findByReader(String reader)

    void save(Book book)
}

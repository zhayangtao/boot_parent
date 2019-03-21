package com.example.boot_groovy

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

/**
 * @author zhayangtao* @version 1.0* @since 2019/01/30
 */
@Repository
class JdbcReadingListRepository implements ReadingListRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    List<Book> findByReader(String reader) {
        String sql = 'select id, reader, isbn, title, author, description from Book where reader=?'
        jdbcTemplate.query(sql, {
            rs, row ->  new Book(id: rs.getLong(1),
                    reader: rs.getString(2),
                    isbn: rs.getString(3),
                    title: rs.getString(4),
                    author: rs.getString(5),
                    description: rs.getString(6))
        } as RowMapper, reader)
    }

    @Override
    void save(Book book) {
        String sql = "insert into Book (reader, isbn, title, author, description) values (?,?,?,?,?)"
        jdbcTemplate.update(sql, book.reader, book.isbn, book.title, book.author, book.description)
    }
}

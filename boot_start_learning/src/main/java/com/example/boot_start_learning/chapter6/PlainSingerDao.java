package com.example.boot_start_learning.chapter6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/19
 */
public class PlainSingerDao implements SingerDao {
    private static Logger logger = LoggerFactory.getLogger(PlainSingerDao.class);
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("Problem loading DB driver", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql:///sampledb?userSSL=true", "root", "1234");
    }

    private void closeConnection(Connection connection) {
        if (connection == null) {
            return;
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("Problem closing connection to the database!", e);
        }
    }

    @Override
    public List<Singer> findAll() {
        return null;
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        return null;
    }

    @Override
    public String findLastNameById(Long id) {
        return null;
    }

    @Override
    public String findFirstNameById(Long id) {
        return null;
    }

    @Override
    public void insert(Singer singer) {

    }

    @Override
    public void update(Singer singer) {

    }

    @Override
    public void delete(Long singerId) {

    }

    @Override
    public List<Singer> findAllWithDetail() {
        return null;
    }

    @Override
    public void insertWithDetail(Singer singer) {

    }

    public static void main(String[] args) {
        System.out.println(62 % 10);
        System.out.println(60 % 10);
    }
}

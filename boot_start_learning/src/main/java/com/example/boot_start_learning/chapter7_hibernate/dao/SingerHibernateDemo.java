package com.example.boot_start_learning.chapter7_hibernate.dao;

import com.example.boot_start_learning.chapter7_hibernate.config.AppConfig;
import com.example.boot_start_learning.chapter7_hibernate.entities.Singer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/24
 */
public class SingerHibernateDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        SingerDao singerDao = context.getBean(SingerDao.class);
//        listSingers(singerDao.findAll());

        singerDao.findAllWithAlbum();
    }

    private static void listSingers(List<Singer> singerList) {
        singerList.forEach(System.out::println);
    }

    private static void listSingersWithAlbum(List<Singer> singers) {
        singers.forEach(singer -> {
            if (singer.getAlbums() != null) {
                singer.getAlbums().forEach(System.out::println);
            }
            if (singer.getInstruments() != null) {
                singer.getInstruments().forEach(instrument -> System.out.println(instrument.getInstrumentId()));
            }
        });
    }
}

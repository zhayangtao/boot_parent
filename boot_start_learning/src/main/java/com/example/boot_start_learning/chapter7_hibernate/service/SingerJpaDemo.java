package com.example.boot_start_learning.chapter7_hibernate.service;

import com.example.boot_start_learning.chapter7_hibernate.config.JpaConfig;
import com.example.boot_start_learning.chapter7_hibernate.entities.Album;
import com.example.boot_start_learning.chapter7_hibernate.entities.Singer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/25
 */

public class SingerJpaDemo {
    private static Logger logger = LoggerFactory.getLogger(SingerJpaDemo.class);
    private GenericApplicationContext context;
    private SingerService singerService;
    private SingerSummaryUntypeImpl singerSummaryUntype;
    private SingerSummaryService singerSummaryService;

    @Before
    public void setUp() {
        context = new AnnotationConfigApplicationContext(JpaConfig.class);
        singerService = context.getBean(SingerService.class);
        singerSummaryUntype = context.getBean(SingerSummaryUntypeImpl.class);
        singerSummaryService = context.getBean(SingerSummaryService.class);
        assertNotNull(singerService);
    }

    @Test
    public void testFindAll() {
        List<Singer> singers = singerService.findAll();
        assertEquals(3, singers.size());
    }

    @Test
    public void testFindAllWithAlbum() {
        List<Singer> singers = singerService.findAllWithAlbum();
        assertEquals(3, singers.size());
    }

    @Test
    public void testFindById() {

    }

    @Test
    public void testFindAllUntype() {
        singerSummaryUntype.displayAllSingerSummary();
    }

    @Test
    public void testSingerSummaryFindAll() {
        List<SingerSummary> singerSummaries = singerSummaryService.findAll();
        singerSummaries.forEach(System.out::println);
    }

    @Test
    public void testSave() {
        Singer singer = new Singer();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(new Date());

        Album album = new Album();
        album.setTitle("My king of blues");
        album.setReleaseDate(new Date());
        album.setSinger(singer);
        singer.addAlbum(album);

        album = new Album();
        album.setTitle("A Heart full of blues");
        album.setReleaseDate(new Date());
        album.setSinger(singer);
        singer.addAlbum(album);
        singerService.save(singer);
        assertNotNull(singer.getId());

    }

    @Test
    public void testUpdate() {
        Singer singer = singerService.findById(1L);
        Album album = singer.getAlbums().stream().filter(album1 -> album1.getTitle().equals("Battle "))
                .findFirst().get();
        singer.setFirstName("Clayton");
        singer.removeAlbum(album);
        singerService.save(singer);
    }

    private static void listSingers(List<Singer> singerList) {
        singerList.forEach(singer -> logger.info(singer.toString()));
    }

    @After
    public void tearDown() {
        context.close();
    }
}

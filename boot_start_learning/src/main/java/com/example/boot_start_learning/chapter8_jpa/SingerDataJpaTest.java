package com.example.boot_start_learning.chapter8_jpa;

import com.example.boot_start_learning.chapter8_jpa.entities.Album;
import com.example.boot_start_learning.chapter8_jpa.entities.Singer;
import com.example.boot_start_learning.config.DataJpaConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * @author no one
 * @version 1.0
 * @since 2019/07/26
 */
public class SingerDataJpaTest {
    private SingerService singerService;
    private AlbumService albumService;

    @Before
    public void setUp() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataJpaConfig.class);
        singerService = context.getBean(SingerService.class);
        albumService = context.getBean(AlbumService.class);
        assertNotNull(singerService);
    }

    @Test
    public void testFindAll() {
        List<Singer> singerList = singerService.findAll();
        assertTrue(singerList.size() > 0);
    }

    @Test
    public void testFindBySinger() {
        List<Singer> singers = singerService.findByFirstName("Clayton");
        albumService.findBySinger(singers.get(0));
    }
    @Test
    public void testFindByTitle() {
        List<Album> albums = albumService.findByTitle("The");
        assertTrue(albums.size() > 0);
        albums.forEach(System.out::println);
    }
}

package com.example.boot_start_learning.chapter18_batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.Nullable;

/**
 * @author no one
 * @version 1.0
 * @since 2019/08/05
 */
public class SingerItemProcessor implements ItemProcessor<Singer, Singer> {
    private static Logger logger = LoggerFactory.getLogger(SingerItemProcessor.class);

    @Override
    public Singer process(@Nullable Singer singer) throws Exception {
        String firstName = singer.getFirstName().toUpperCase();
        String lastName = singer.getLastName().toUpperCase();
        String song = singer.getSong().toUpperCase();

        Singer transformedSinger = new Singer();
        transformedSinger.setFirstName(firstName);
        transformedSinger.setLastName(lastName);
        transformedSinger.setSong(song);

        logger.info("Transformed singer: " + singer + " info:" + transformedSinger);
        return transformedSinger;
    }
}

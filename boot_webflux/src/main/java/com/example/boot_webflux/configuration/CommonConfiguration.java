package com.example.boot_webflux.configuration;

import com.example.boot_webflux.entity.MyEvent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/27
 */
@Configuration
public class CommonConfiguration {

    @Bean
    public CommandLineRunner initData(MongoOperations mongoOperations) {
        return args -> {
            mongoOperations.dropCollection(MyEvent.class);
            mongoOperations.createCollection(MyEvent.class, CollectionOptions.empty().size(200).capped());
        };
    }
}

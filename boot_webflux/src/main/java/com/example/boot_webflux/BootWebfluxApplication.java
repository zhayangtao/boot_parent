package com.example.boot_webflux;

import com.example.boot_webflux.entity.MyEvent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoOperations;

@SpringBootApplication
public class BootWebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootWebfluxApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(MongoOperations mongoOperations) {
        return args -> {
            mongoOperations.dropCollection(MyEvent.class);
            mongoOperations.createCollection(MyEvent.class, CollectionOptions.empty().maxDocuments(200).size(100000).capped());
        };
    }
}

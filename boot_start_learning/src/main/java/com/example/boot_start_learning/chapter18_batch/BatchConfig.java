package com.example.boot_start_learning.chapter18_batch;

import com.example.boot_start_learning.config.DataJpaConfig;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;

/**
 * @author no one
 * @version 1.0
 * @since 2019/08/05
 */
@Configuration
@Import(DataJpaConfig.class)
@EnableBatchProcessing
@ComponentScan(basePackages = "com.example.boot_start_learning.chapter18_batch")
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private StepExecutionStatsListener stepExecutionStatsListener;

    /*@Bean
    protected Step step1(ItemReader<Singer> reader, ItemProcessor<Singer, Singer> itemProcessor, ItemWriter<Singer> writer) {
        return stepBuilderFactory.get("step1").listener(stepExecutionStatsListener).chunk(10)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .build();
    }*/

    @Bean
    public ItemReader itemReader() {
        FlatFileItemReader itemReader = new FlatFileItemReader();
        itemReader.setResource(resourceLoader.getResource(""));
        return null;
    }
}

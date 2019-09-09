package com.example.boot_start_learning.chapter18_batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * @author no one
 * @version 1.0
 * @since 2019/08/05
 */
@Component
public class StepExecutionStatsListener extends StepExecutionListenerSupport {
    private static Logger logger = LoggerFactory.getLogger(StepExecutionStatsListener.class);

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.info("--> Wrote: " + stepExecution.getWriteCount() + " items in step:" + stepExecution.getStepName());
        return null;
    }
}

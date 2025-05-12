package com.maxicorrea.github.java_spring_boot_scheduler_async.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobTwo {

    private static final Logger logger = LoggerFactory.getLogger(JobTwo.class);

    @Scheduled(fixedRate = 5000)
    @Async(value = "taskExecutor")
    public void execute() {
        logger.debug("Executing Job Two");
    }

}

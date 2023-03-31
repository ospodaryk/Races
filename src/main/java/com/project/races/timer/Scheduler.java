package com.project.races.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private KafkaProducer kafkaProducer;

    @Scheduled(fixedDelay = 10000)
    public void scheduleTask() {
        logger.info("________________Scheduler");
        System.out.println("________________Scheduler");
        kafkaProducer.sendMessage(new MyMessage("1","jejej"));
    }
}

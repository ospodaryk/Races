package com.project.races.communnication;

import com.project.races.model.Race;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class RaceConsumer {

    private static final Logger logger = LoggerFactory.getLogger(RaceConsumer.class);

    @KafkaListener(topics = "race-topic-0", groupId = "race-consumer-group")
    public void consume(Race race) {
        try {
            logger.info("Received race message: {}", race);
        } catch (Exception ex) {
            logger.error("Error while processing race message: {}", ex.getMessage(), ex);
        }
    }
}

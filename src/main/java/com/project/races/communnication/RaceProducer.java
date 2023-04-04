package com.project.races.communnication;

import com.project.races.model.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class RaceProducer {

    private static final Logger logger = LoggerFactory.getLogger(RaceProducer.class);

    private static final String TOPIC = "race-topic";

    @Autowired
    private KafkaTemplate<String, Race> kafkaTemplate;

    public void sendMessage(Race race) {
        if (race != null) {
            kafkaTemplate.send(TOPIC, race);
            logger.info("Sent race message: {}", race);
        } else {
            logger.error("Cannot send null race message");
        }
    }
}

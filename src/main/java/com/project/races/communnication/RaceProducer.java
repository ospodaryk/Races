package com.project.races.communnication;

import com.project.races.model.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class RaceProducer {

    private static final String TOPIC = "race-topic";

    @Autowired
    private KafkaTemplate<String, Race> kafkaTemplate;

    public void sendMessage(Race race) {
        kafkaTemplate.send(TOPIC, race);
    }

}
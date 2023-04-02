package com.project.races.communnication;

import com.project.races.model.Race;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component
public class RaceProducer {

    private static final String TOPIC = "race-topic";

    @Autowired
    private KafkaTemplate<String, Race> kafkaTemplate;

    public void sendMessage(Race race) {
        kafkaTemplate.send(TOPIC, race);
    }

}
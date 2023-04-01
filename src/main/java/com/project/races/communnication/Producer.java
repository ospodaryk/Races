package com.project.races.communnication;

import com.project.races.model.Race;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private KafkaTemplate<String, Race> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private String topicName;

    public void sendMessage(Race message) {
        logger.info("____SEND" + message);
        kafkaTemplate.send(topicName, updateRace(message));
    }

    private Race updateRace(Race race) {
        race.getTeams().get(1).setScore(1L);
        race.getTeams().get(2).setScore(2L);
        race.getTeams().get(3).setScore(3L);
        return race;
    }
}

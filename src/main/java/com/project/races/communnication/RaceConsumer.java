package com.project.races.communnication;

import com.project.races.model.Race;
import com.project.races.service.RaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.project.races.model.Race;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class RaceConsumer {

    @KafkaListener(topics = "race-topic-0", groupId = "race-consumer-group")
    public void consume(Race race) {
        try {
            // Process the incoming race message
            System.out.println("Received race message: " + race);
        } catch (Exception ex) {
            // Handle the exception and log the error message
            System.err.println("Error while processing race message: " + ex.getMessage());
        }
    }
}

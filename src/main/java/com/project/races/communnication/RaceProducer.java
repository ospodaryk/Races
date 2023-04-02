package com.project.races.communnication;

import com.project.races.model.Race;
import com.project.races.model.Team;
import com.project.races.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RaceProducer {

    private static final String TOPIC = "race-topic";
    @Autowired
    private RaceService raceService;
    @Autowired
    private KafkaTemplate<String, Race> kafkaTemplate;

    public RaceProducer(RaceService raceService, KafkaTemplate<String, Race> kafkaTemplate) {
        this.raceService = raceService;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Race race) {
        kafkaTemplate.send(TOPIC, updateRace(race));
    }

    private Race updateRace(Race race) {
        List<Team> allTeams = race.getTeams();
//        int index1 = new Random().nextInt(allTeams.size());
//        int index2 = new Random().nextInt(allTeams.size() - 1);
//        int index3 = new Random().nextInt(allTeams.size() - 2);
        race.getTeams().get(3).setScore(1L);
        race.getTeams().get(1).setScore(2L);
        race.getTeams().get(2).setScore(3L);
//        race.getTeams().get(index1).setScore(1L);
        raceService.update(race);
//        allTeams.remove(index1);
//
//        while (index2 == index1) {
//            index2 = new Random().nextInt(allTeams.size());
//        }
//        race.getTeams().get(index2).setScore(2L);
//        allTeams.remove(index2);
//
//
//        while ((index2 == index3) && (index1 == index3)) {
//            index3 = new Random().nextInt(allTeams.size());
//        }
//        race.getTeams().get(index3).setScore(3L);
//        allTeams.remove(index3);
        return race;
    }

}
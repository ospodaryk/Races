package com.project.races.communnication;

import com.project.races.model.Race;
import com.project.races.service.RaceService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class Scheduler {
    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private RaceProducer raceProducer;

    @Autowired
    private SchedulerConfig schedulerConfig;

    //    @Scheduled(fixedDelay = 10000)
    @Scheduled(cron = "#{@schedulerConfig.getCronExpression()}")
    public void scheduleTask() {
        logger.info("Sending scheduled message...");
        System.out.println("Scheduled");
        System.out.println("______________________" + schedulerConfig.getRace().toString());
        raceProducer.sendMessage(schedulerConfig.getRace());
    }
}

@Data
@Component
class SchedulerConfig {
    private final String cronExpression;
    private Race race;

    @Autowired
    public SchedulerConfig(RaceService raceService) {
        System.out.println("___CONSTRUCTOR");
        List<Race> allRaces = raceService.getAll();
        int randomIndex = new Random().nextInt(allRaces.size());
        this.race = allRaces.get(randomIndex);
        this.cronExpression = replaceLastFourWithAsterisks(this.race.getDateOfStart());
    }

    public String getCronExpression() {
        return this.cronExpression;
    }

    public Race getRace() {
        System.out.println("_______getRace");
        return this.race;
    }

//    private Race getRandomRace(RaceService raceService) {
//        System.out.println("_______getRandomRace");
//        List<Race> allRaces = raceService.getAll();
//        int randomIndex = new Random().nextInt(allRaces.size());
//        this.race= allRaces.get(randomIndex);
//        return race;
//    }

    private static String replaceLastFourWithAsterisks(String str) {
        System.out.println("_______replaceLastFourWithAsterisks");
        int length = str.length();
        if (length < 4) {
            return str;
        } else {
            String asterisks = "*";
            return str.substring(0, length - 4) + asterisks;
        }
    }
}
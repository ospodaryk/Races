package com.project.races.communnication;

import com.project.races.model.Race;
import com.project.races.repository.RaceRepository;
import com.project.races.service.RaceService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Data
@Component
public class Scheduler {
    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);
    private Producer producer;
    private SchedulerConfig schedulerConfig;
    private RaceService raceService;

    @Autowired
    public Scheduler(Producer producer, SchedulerConfig schedulerConfig, RaceService raceService) {
        this.producer = producer;
        this.schedulerConfig = schedulerConfig;
        this.raceService = raceService;
    }

    @Scheduled(cron = "#{@schedulerConfig.getCronExpression()}")
    public void scheduleTask() {
        producer.sendMessage(schedulerConfig.getRace());
    }
}

@Data
@Component
class SchedulerConfig {
    private final String cronExpression;
    private final Race race;

    @Autowired
    public SchedulerConfig(RaceService raceService) {
        this.race = getRandomRace(raceService);
        this.cronExpression = replaceLastFourWithAsterisks(race.getDateOfStart());
    }

    public String getCronExpression() {
        return this.cronExpression;
    }

    public Race getRace() {
        return this.race;
    }

    private Race getRandomRace(RaceService raceService) {
        List<Race> allRaces = raceService.getAll();
        int randomIndex = new Random().nextInt(allRaces.size());
        return allRaces.get(randomIndex);
    }

    private static String replaceLastFourWithAsterisks(String str) {
        int length = str.length();
        if (length < 4) {
            // the string is too short to replace, return it as-is
            return str;
        } else {
            // replace the last 4 characters with asterisks
            String asterisks = "*";
            return str.substring(0, length - 4) + asterisks;
        }
    }
}

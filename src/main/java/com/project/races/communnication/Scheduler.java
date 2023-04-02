package com.project.races.communnication;

import com.project.races.model.Race;
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

@Component
public class Scheduler {
    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private RaceProducer raceProducer;

    @Autowired
    private SchedulerConfig schedulerConfig;

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
        updateDate(raceService);

        this.cronExpression = replaceLastFourWithAsterisks(this.race.getDateOfStart());
    }

    public String getCronExpression() {
        return this.cronExpression;
    }

    public Race getRace() {
        System.out.println("_______getRace");
        return this.race;
    }

    private void updateDate(RaceService raceService) {
        this.race.setDateOfStart(replaceMinute());
        raceService.update(this.race);
    }


    private String replaceMinute() {
        LocalDateTime time = LocalDateTime.now().plusSeconds(10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ss mm HH dd MM yyyy");
        return time.format(formatter);
    }

    private String replaceLastFourWithAsterisks(String str) {
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

package com.project.races.communnication;

import com.project.races.model.Race;
import com.project.races.model.Team;
import com.project.races.service.RaceService;
import com.project.races.service.TeamService;
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

    private final RaceProducer raceProducer;
    private final SchedulerConfig schedulerConfig;
    private final TeamService teamService;
    private final RaceService raceService;

    @Autowired
    public Scheduler(RaceProducer raceProducer, SchedulerConfig schedulerConfig, TeamService teamService, RaceService raceService) {
        this.raceProducer = raceProducer;
        this.schedulerConfig = schedulerConfig;
        this.teamService = teamService;
        this.raceService = raceService;
    }

    @Scheduled(cron = "#{@schedulerConfig.getCronExpression()}")
    public void scheduleTask() {
        try {
            logger.info("Sending scheduled message...");
            System.out.println("Sending scheduled message...");
            updateTeam(teamService);
            raceProducer.sendMessage(schedulerConfig.getRace());
        } catch (Exception ex) {
            logger.error("Error while scheduling task: {}", ex.getMessage());
        }
    }

    private void updateTeam(TeamService teamService) {
        Race race = schedulerConfig.getRace();
        int size = race.getTeams().size() - 1;

        int index1 = new Random().nextInt(size) + 1;
        int index2 = new Random().nextInt(size) + 1;
        int index3 = new Random().nextInt(size) + 1;

        while (index2 == index1) {
            index2 = new Random().nextInt(size) + 1;
        }
        while ((index3 == index1) || (index3 == index2)) {
            index3 = new Random().nextInt(size) + 1;
        }

        Team team1 = raceService.getByIdTeam(race.getId(), index1);
        team1.setScore(10L);
        logger.info("Winner - " + team1);

        Team team2 = raceService.getByIdTeam(race.getId(), index2);
        team2.setScore(5L);
        logger.info("2 place - " + team2);

        Team team3 = raceService.getByIdTeam(race.getId(), index3);
        team3.setScore(1L);
        logger.info("2 place - " + team3);

        teamService.update(team1);
        teamService.update(team2);
        teamService.update(team3);
    }
}

@Data
@Component
class SchedulerConfig {
    private final String cronExpression;
    private final Race race;
    private static final Logger logger = LoggerFactory.getLogger(SchedulerConfig.class);

    public SchedulerConfig(RaceService raceService) {
        List<Race> allRaces = raceService.getAll();
        int randomIndex = new Random().nextInt(allRaces.size());
        this.race = allRaces.get(randomIndex);
        try {
            updateDate(raceService);
        } catch (Exception e) {
            logger.error("Error while updating date: {}", e.getMessage());
        }
        this.cronExpression = replaceLastFourWithAsterisks(this.race.getDateOfStart());
    }

    public String getCronExpression() {
        return this.cronExpression;
    }

    public Race getRace() {
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
        int length = str.length();
        if (length < 4) {
            return str;
        } else {
            String asterisks = "*";
            return str.substring(0, length - 4) + asterisks;
        }
    }
}

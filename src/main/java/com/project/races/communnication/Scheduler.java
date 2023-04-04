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

    @Autowired
    public Scheduler(RaceProducer raceProducer, SchedulerConfig schedulerConfig, TeamService teamService) {
        this.raceProducer = raceProducer;
        this.schedulerConfig = schedulerConfig;
        this.teamService = teamService;
    }


    @Scheduled(cron = "#{@schedulerConfig.getCronExpression()}")
    public void scheduleTask() {
        logger.info("Sending scheduled message...");
        System.out.println("Scheduled");
        System.out.println("______________________" + schedulerConfig.getRace().toString());
        updateTeam(teamService);
        raceProducer.sendMessage(schedulerConfig.getRace());
    }


    private void updateTeam(TeamService teamService) {
        System.out.println("updateTeam");
        System.out.println("___________________"+schedulerConfig.getRace().getTeams().toString());
        Team team = schedulerConfig.getRace().getTeams().get(0);
        System.out.println(team.toString());

        team.setName("Taras");
        teamService.update(team);
    }

}

@Data
@Component
class SchedulerConfig {
    private final String cronExpression;
    private final Race race;

    public SchedulerConfig(RaceService raceService) {
        System.out.println("___CONSTRUCTOR");
        List<Race> allRaces = raceService.getAll();
        int randomIndex = new Random().nextInt(allRaces.size());
        this.race = allRaces.get(0);
        try {
            updateDate(raceService);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
//            updateTeam(teamService);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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

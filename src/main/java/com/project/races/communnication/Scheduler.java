package com.project.races.communnication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Component
public class Scheduler {
    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private Producer producer;

    @Autowired
    private SchedulerConfig schedulerConfig;

    @Scheduled(cron = "#{@schedulerConfig.getCronExpression()}")
    public void scheduleTask() {
        producer.sendMessage(new MyMessage("1", "jejej"));
    }
}

@Component
class SchedulerConfig {
    private final String cronExpression;

    public SchedulerConfig() {
        LocalDateTime date = LocalDateTime.of(2023, 04, 1, 23, 39, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ss mm HH dd MM *");
        System.out.println(date.format(formatter));
        this.cronExpression = date.format(formatter);
    }

    public String getCronExpression() {
        return this.cronExpression;
    }
}

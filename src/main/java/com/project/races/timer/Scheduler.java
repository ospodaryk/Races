package com.project.races.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Component
public class Scheduler {
    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private SchedulerConfig schedulerConfig;

    @Scheduled(cron = "#{@schedulerConfig.getCronExpression()}")
    public void scheduleTask() {
        kafkaProducer.sendMessage(new MyMessage("1", "jejej"));
    }
}

@Component
class SchedulerConfig {
    private final String cronExpression;

    public SchedulerConfig() {
        LocalDateTime date = LocalDateTime.of(2023, 03, 31, 12, 43, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ss mm HH dd MM *");
        System.out.println(date.format(formatter));
        this.cronExpression = date.format(formatter);
    }

    public String getCronExpression() {
//        return "00 41 12 31 03 *";
        return this.cronExpression;
    }
}

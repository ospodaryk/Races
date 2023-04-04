package com.project.races;

import com.project.races.dto.pilot.PilotTransformer;
import com.project.races.dto.race.RaceTransformer;
import com.project.races.dto.team.TeamTransformer;
import com.project.races.service.PilotService;
import com.project.races.service.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RacesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RacesApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PilotTransformer userTransformer(ModelMapper modelMapper, PilotService pilotService) {
        return new PilotTransformer(modelMapper, pilotService);
    }

    @Bean
    public TeamTransformer teamTransformer(ModelMapper modelMapper, TeamService teamService) {
        return new TeamTransformer(modelMapper, teamService);
    }

    @Bean
    public RaceTransformer raceTransformer(ModelMapper modelMapper, TeamService teamService) {
        return new RaceTransformer(modelMapper, teamService);
    }
}

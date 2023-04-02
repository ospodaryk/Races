package com.project.races;

import com.project.races.dto.pilot.PilotTransformer;
import com.project.races.dto.race.RaceTransformer;
import com.project.races.dto.todo.TeamTransformer;
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
    public PilotTransformer userTransformer(ModelMapper modelMapper) {
        return new PilotTransformer(modelMapper);
    }

    @Bean
    public TeamTransformer teamTransformer(ModelMapper modelMapper) {
        return new TeamTransformer(modelMapper);
    }

    @Bean
    public RaceTransformer raceTransformer(ModelMapper modelMapper) {
        return new RaceTransformer(modelMapper);
    }
}

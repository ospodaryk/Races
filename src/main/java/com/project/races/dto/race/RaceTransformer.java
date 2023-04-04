package com.project.races.dto.race;

import com.project.races.model.Race;
import com.project.races.service.TeamService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class RaceTransformer {
    private static final Logger logger = LoggerFactory.getLogger(RaceTransformer.class);

    private final ModelMapper modelMapper;
    private final TeamService teamService;

    public RaceTransformer(ModelMapper modelMapper, TeamService teamService) {
        this.modelMapper = modelMapper;
        this.teamService = teamService;
    }

    public Race convertRaceRequestToRace(RaceRequest raceRequest) {
        try {
            logger.info("Converting race request to race");
            Race race = modelMapper.map(raceRequest, Race.class);
            race.setTeams(new ArrayList<>());
            logger.info("Race request converted to race");
            return race;
        } catch (Exception e) {
            logger.error("Error converting race request to race: {}", e.getMessage());
            throw e;
        }
    }

    public RaceResponse convertToRaceResponse(Race race) {
        try {
            logger.info("Converting race to race response");
            RaceResponse raceResponse = modelMapper.map(race, RaceResponse.class);
            raceResponse.setId(race.getId());
            raceResponse.setName(race.getName());
            raceResponse.setStadium(race.getStadium());
            raceResponse.setDateOfStart(race.getDateOfStart());
            raceResponse.setNumberOfLaps(race.getNumberOfLaps());
            raceResponse.setTeamsID(race.getTeams().stream().map(ob -> ob.getId()).collect(Collectors.toList()));
            raceResponse.setTrackName(race.getTrackName());
            logger.info("Race converted to race response");
            return raceResponse;
        } catch (Exception e) {
            logger.error("Error converting race to race response: {}", e.getMessage());
            throw e;
        }
    }
}


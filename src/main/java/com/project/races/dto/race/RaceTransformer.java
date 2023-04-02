package com.project.races.dto.race;

import com.project.races.model.Race;
import org.modelmapper.ModelMapper;

import java.util.stream.Collectors;


public class RaceTransformer {
    private final ModelMapper modelMapper;

    public RaceTransformer(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Race convertRaceRequestToRace(RaceRequest raceRequest) {
        Race race = modelMapper.map(raceRequest, Race.class);
        return race;
    }

    public RaceResponse convertToRaceResponse(Race race) {
        RaceResponse raceResponse = modelMapper.map(race, RaceResponse.class);
        raceResponse.setId(race.getId());
        raceResponse.setName(race.getName());
        raceResponse.setStadium(race.getStadium());
        raceResponse.setDateOfStart(race.getDateOfStart());
        raceResponse.setNumberOfLaps(race.getNumberOfLaps());
        raceResponse.setTeamsID(race.getTeams().stream().map(ob -> ob.getId()).collect(Collectors.toList()));
        raceResponse.setTrackName(race.getTrackName());
        return raceResponse;
    }
}

package com.project.races.dto.user;

import com.project.races.dto.todo.TeamResponse;
import com.project.races.model.Race;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class RaceTransformer {
    private final ModelMapper modelMapper;


    public RaceTransformer(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Race convertRaceRequestToRace(RaceRequest raceRequest) {
        Race race=modelMapper.map(raceRequest, Race.class);
        race.setTeams(new ArrayList<>());
        return race;
    }

    public RaceResponse convertToRaceResponse(Race race) {
        RaceResponse raceResponse = modelMapper.map(race, RaceResponse.class);
        raceResponse.setTeamsID(race.getTeams().stream().map(ob -> ob.getId()).collect(Collectors.toList()));
        return raceResponse;
    }
}

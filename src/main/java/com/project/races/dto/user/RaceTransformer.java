package com.project.races.dto.user;

import com.project.races.model.Race;
import org.modelmapper.ModelMapper;


public class RaceTransformer {
    private final ModelMapper modelMapper;


    public RaceTransformer(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Race convertRaceRequestToRace(RaceRequest raceRequest) {
        return modelMapper.map(raceRequest, Race.class);
    }

    public RaceResponse convertToRaceResponse(Race user) {
        return modelMapper.map(user, RaceResponse.class);
    }
}

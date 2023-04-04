package com.project.races.dto.pilot;


import com.project.races.model.Pilot;
import com.project.races.model.Team;
import com.project.races.service.PilotService;
import com.project.races.service.RaceService;
import com.project.races.service.TeamService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.stream.Collectors;

public class PilotTransformer {
    static  Long count=0L;
    private final ModelMapper modelMapper;
    private static final Logger logger = LoggerFactory.getLogger(PilotTransformer.class);
    private final PilotService pilotService;

    public PilotTransformer(ModelMapper modelMapper, PilotService pilotService) {
        this.modelMapper = modelMapper;
        this.pilotService = pilotService;
    }

    public Pilot convertPilotRequestToPilot(PilotRequest pilotRequest, Team team) {
        logger.info("convertRecipeRequestToRecipe");
        Pilot pilot = modelMapper.map(pilotRequest, Pilot.class);
        pilot.setTeam(team);
        Set<Long> a = pilotService.getAll().stream().map(obj -> obj.getNumber()).collect(Collectors.toSet());
        if (!a.contains(pilotRequest.getNumber())) {
            count++;
        }
        pilot.setStaticNumber(count);
        pilot.setScore(0L);
        return pilot;
    }


    public PilotResponse convertPilotToPilotResponse(Pilot pilot) {
        logger.info("convertToRecipeResponse");
        PilotResponse pilotResponse = new PilotResponse();
        pilotResponse.setStaticNumber(pilot.getStaticNumber());
        pilotResponse.setId(pilot.getId());
        pilotResponse.setNumber(pilot.getNumber());
        pilotResponse.setCountry(pilot.getCountry());
        pilotResponse.setName(pilot.getName());
        pilotResponse.setSurname(pilot.getSurname());
        pilotResponse.setTeamID(pilot.getTeam().getId());
        pilotResponse.setScore(pilot.getScore());

        return pilotResponse;
    }

}

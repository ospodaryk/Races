package com.project.races.dto.pilot;


import com.project.races.model.Pilot;
import com.project.races.model.Team;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PilotTransformer {
    private  final ModelMapper modelMapper;
    private static final Logger logger = LoggerFactory.getLogger(PilotTransformer.class);

    public PilotTransformer(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public  Pilot convertPilotRequestToPilot(PilotRequest pilotRequest, Team team) {
        logger.info("convertRecipeRequestToRecipe");
        Pilot pilot=modelMapper.map(pilotRequest, Pilot.class);
        pilot.setTeam(team);
        pilot.setScore(0L);
        return pilot;
    }


    public  PilotResponse convertPilotToPilotResponse(Pilot pilot) {
        logger.info("convertToRecipeResponse");
        PilotResponse pilotResponse=new PilotResponse();
        pilotResponse.setId(pilot.getId());
        pilotResponse.setNumber(pilot.getNumber());
        pilotResponse.setCountry(pilot.getCountry());
        pilotResponse.setName(pilot.getName());
        pilotResponse.setSurname(pilot.getSurname());
        pilotResponse.setTeamID(pilot.getTeam().getId());
        pilotResponse.setScore(pilot.getScore());

        return  pilotResponse;
    }

}

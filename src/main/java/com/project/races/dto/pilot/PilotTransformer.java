package com.project.races.dto.pilot;


import com.project.races.model.Pilot;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PilotTransformer {
    private final ModelMapper modelMapper;
    private static final Logger logger = LoggerFactory.getLogger(PilotTransformer.class);

    public PilotTransformer(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Pilot convertPilotRequestToPilot(PilotRequest pilotRequest) {
        logger.info("convertRecipeRequestToRecipe");
        return modelMapper.map(pilotRequest, Pilot.class);
    }


    public PilotResponse convertPilotToPilotResponse(Pilot pilot) {
        logger.info("convertToRecipeResponse");
        return  modelMapper.map(pilot, PilotResponse.class);
    }

}

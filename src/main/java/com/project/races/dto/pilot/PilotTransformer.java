package com.project.races.dto.pilot;


import com.project.races.model.Pilot;
import com.project.races.model.Team;
import com.project.races.service.PilotService;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class PilotTransformer {
    static Long count = 0L;
    private final ModelMapper modelMapper;
    private static final Logger logger = LoggerFactory.getLogger(PilotTransformer.class);
    private final PilotService pilotService;

    public PilotTransformer(ModelMapper modelMapper, PilotService pilotService) {
        this.modelMapper = modelMapper;
        this.pilotService = pilotService;
    }

    public Pilot convertPilotRequestToPilot(PilotRequest pilotRequest, Team team) {
        try {
            logger.info("Converting pilot request to pilot");
            Pilot pilot = modelMapper.map(pilotRequest, Pilot.class);
            pilot.setTeam(team);
            Set<Long> a = pilotService.getAll().stream().map(obj -> obj.getNumber()).collect(Collectors.toSet());
            if (!a.contains(pilotRequest.getNumber())) {
                count++;
            }
            pilot.setStaticNumber(count);
            pilot.setScore(0L);
            logger.info("Pilot request converted to pilot");
            return pilot;
        } catch (Exception e) {
            logger.error("Error converting pilot request to pilot: {}", e.getMessage());
            throw e;
        }
    }

    public PilotResponse convertPilotToPilotResponse(Pilot pilot) {
        try {
            logger.info("Converting pilot to pilot response");
            PilotResponse pilotResponse = new PilotResponse();
            pilotResponse.setStaticNumber(pilot.getStaticNumber());
            pilotResponse.setId(pilot.getId());
            pilotResponse.setNumber(pilot.getNumber());
            pilotResponse.setCountry(pilot.getCountry());
            pilotResponse.setName(pilot.getName());
            pilotResponse.setSurname(pilot.getSurname());
            pilotResponse.setTeamID(pilot.getTeam().getId());
            pilotResponse.setScore(pilot.getScore());
            logger.info("Pilot converted to pilot response");
            return pilotResponse;
        } catch (Exception e) {
            logger.error("Error converting pilot to pilot response: {}", e.getMessage());
            throw e;
        }
    }
}

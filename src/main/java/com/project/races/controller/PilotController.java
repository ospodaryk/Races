package com.project.races.controller;

import com.project.races.dto.pilot.PilotRequest;
import com.project.races.dto.pilot.PilotResponse;
import com.project.races.dto.pilot.PilotTransformer;
import com.project.races.model.Pilot;
import com.project.races.model.Team;
import com.project.races.service.PilotService;
import com.project.races.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pilots")
public class PilotController {
    private static final Logger logger = LoggerFactory.getLogger(PilotController.class);
    private PilotTransformer pilotTransformer;
    private PilotService pilotService;
    private TeamService teamService;

    public PilotController(PilotTransformer pilotTransformer, PilotService pilotService, TeamService teamService) {
        this.pilotTransformer = pilotTransformer;
        this.pilotService = pilotService;
        this.teamService = teamService;
    }

    @GetMapping("/{pilot_id}")
    public PilotResponse getPilotById(@PathVariable("pilot_id") long pilot_id) {
        try {
            logger.info("Getting pilot with id {}", pilot_id);
            Pilot pilot = pilotService.getById(pilot_id);
            return pilotTransformer.convertPilotToPilotResponse(pilot);
        } catch (Exception e) {
            logger.error("Error occurred while getting pilot with id {}", pilot_id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while getting pilot");
        }
    }

    @GetMapping
    public List<PilotResponse> getAllPilots() {
        try {
            logger.info("Getting all pilots");
            List<Pilot> pilots = pilotService.getAll();
            return pilots.stream().map(pilotTransformer::convertPilotToPilotResponse).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error occurred while getting all pilots", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while getting pilots");
        }
    }

    @DeleteMapping("/{pilot_id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("pilot_id") long pilot_id) {
        try {
            logger.info("Deleting pilot with id {}", pilot_id);
            pilotService.delete(pilot_id);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while deleting pilot with id {}", pilot_id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while deleting pilot");
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            logger.info("Deleting all pilots");
            pilotService.deleteAll();
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while deleting all pilots", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while deleting pilots");
        }
    }

    @PostMapping("/{team_id}/create")
    public ResponseEntity<HttpStatus> create(@RequestBody PilotRequest pilotRequest, @PathVariable("team_id") long team_id) {
        try {
            logger.info("Creating pilot for team with id {}", team_id);
            List<Team> teamList = teamService.getAll().stream().filter(obj -> obj.getStaticNumber().equals(team_id)).collect(Collectors.toList());
            for (Team team : teamList) {
                Pilot pilot = pilotTransformer.convertPilotRequestToPilot(pilotRequest, team);
                pilotService.create(pilot);
            }
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error occurred while creating pilot for team with id {}", team_id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while creating pilot");
        }
    }

    @PutMapping("/{team_id}/{pilot_id}/update")
    public ResponseEntity<HttpStatus> update(@RequestBody PilotRequest pilotRequest, @PathVariable("team_id") long team_id, @PathVariable("pilot_id") long pilot_id) {
        try {
            logger.info("Updating pilot with id {} and team id {}", pilot_id, team_id);
            var teamList = teamService.getAll().stream().filter(obj -> obj.getStaticNumber().equals(team_id)).collect(Collectors.toList());
            for (int i = 0; i < teamList.size(); i++) {
                Pilot pilot = pilotTransformer.convertPilotRequestToPilot(pilotRequest, teamList.get(i));
                pilot.setId(pilot_id);
                pilotService.update(pilot);
            }
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while updating pilot {} for team with id {}", pilot_id, team_id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while updating pilot");
        }
    }
}

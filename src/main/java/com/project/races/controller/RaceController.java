package com.project.races.controller;

import com.project.races.dto.race.RaceRequest;
import com.project.races.dto.race.RaceResponse;
import com.project.races.dto.race.RaceTransformer;
import com.project.races.model.Race;
import com.project.races.service.RaceService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/race")
public class RaceController {
    private final RaceService raceService;
    private final RaceTransformer raceTransformer;
    private static final Logger logger = LoggerFactory.getLogger(RaceController.class);

    public RaceController(RaceService raceService, RaceTransformer raceTransformer) {
        this.raceService = raceService;
        this.raceTransformer = raceTransformer;
    }

    @GetMapping
    public List<RaceResponse> getAllRaces() {
        logger.info("Getting all races");
        List<Race> races = raceService.getAll();
        List<RaceResponse> raceResponses = races.stream()
                .map(raceTransformer::convertToRaceResponse)
                .collect(Collectors.toList());
        logger.info("Returning all races");
        return raceResponses;
    }

    @GetMapping("/{race_id}")
    public RaceResponse getRaceById(@PathVariable long race_id) {
        logger.info("Getting race with ID: {}", race_id);
        Race race = raceService.getById(race_id);
        RaceResponse raceResponse = raceTransformer.convertToRaceResponse(race);
        logger.info("Returning race with ID: {}", race_id);
        return raceResponse;
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllRaces() {
        logger.info("Deleting all races");
        raceService.deleteAll();
        logger.info("All races deleted");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{race_id}")
    public ResponseEntity<HttpStatus> deleteRace(@PathVariable long race_id) {
        logger.info("Deleting race with ID: {}", race_id);
        raceService.delete(race_id);
        logger.info("Race with ID: {} deleted", race_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createRace(@RequestBody @Valid RaceRequest raceRequest) {
        logger.info("Creating new race");
        raceService.create(raceTransformer.convertRaceRequestToRace(raceRequest));
        logger.info("New race created");
        return ResponseEntity.ok(HttpStatus.CREATED);
    }


    @PutMapping(value = "/{race_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> updateRace(@PathVariable("race_id") Long race_id, @RequestBody @Valid RaceRequest raceRequest) {
        logger.info("Updating race with ID: {}", race_id);
        Race newRace = raceTransformer.convertRaceRequestToRace(raceRequest);
        newRace.setId(race_id);
        raceService.update(newRace);
        logger.info("Race with ID: {} updated", race_id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpStatus> handleException(Exception ex) {
        logger.error("An exception occurred: {}", ex.getMessage());
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

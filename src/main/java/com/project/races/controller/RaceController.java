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
    List<RaceResponse> getAll() {
        logger.info("@Get: getAllUsers()");
//        return raceService.getAll();
        return raceService.getAll().stream()
                .map(raceTransformer::convertToRaceResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    RaceResponse getUser(@PathVariable long id) {
        logger.info("@Get: getUser(), id=" + id);
        return raceTransformer.convertToRaceResponse(raceService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id) {
        raceService.delete(id);
        logger.info("@Delete: deleteUser(), id=" + id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createUser(@RequestBody @Valid RaceRequest userRequest,
                                                 BindingResult bindingResult) {

        raceService.create(raceTransformer.convertRaceRequestToRace(userRequest));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }



}

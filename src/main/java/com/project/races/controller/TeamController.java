package com.project.races.controller;

import com.project.races.dto.pilot.PilotResponse;
import com.project.races.dto.todo.TeamRequest;
import com.project.races.dto.todo.TeamResponse;
import com.project.races.dto.todo.TeamTransformer;
import com.project.races.model.Pilot;
import com.project.races.model.Team;
import com.project.races.service.RaceService;
import com.project.races.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/team")
public class TeamController {
    private final RaceService raceService;
    private final TeamService teamService;
    private final TeamTransformer teamTransformer;

    private static final Logger logger = LoggerFactory.getLogger(TeamController.class);

    public TeamController(RaceService raceService, TeamService teamService, TeamTransformer teamTransformer) {
        this.raceService = raceService;
        this.teamService = teamService;
        this.teamTransformer = teamTransformer;
    }

    @GetMapping
    List<TeamResponse> getAllTeams() {
        logger.info("@Get: getAllToDO()");
        return teamService.getAll().stream()
                .map(teamTransformer::convertTeamToTeamResponse)
                .collect(Collectors.toList());
    }


    @PostMapping("/create/team/{race_id}")
    public ResponseEntity<HttpStatus> create(@PathVariable("race_id") long race_id, @RequestBody TeamRequest teamRequest, BindingResult bindingResult) {
        teamService.create(teamTransformer.convertTeamRequestToTeam(teamRequest));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}

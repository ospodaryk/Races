package com.project.races.controller;

import com.project.races.dto.team.TeamRequest;
import com.project.races.dto.team.TeamResponse;
import com.project.races.dto.team.TeamTransformer;
import com.project.races.model.Race;
import com.project.races.model.Team;
import com.project.races.service.RaceService;
import com.project.races.service.TeamService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.apache.kafka.common.errors.ResourceNotFoundException;
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
    public List<TeamResponse> getAllTeams() {
        logger.info("Retrieving all teams");
        List<Team> teams = teamService.getAll();
        List<TeamResponse> teamResponses = teams.stream().map(teamTransformer::convertTeamToTeamResponse).collect(Collectors.toList());
        logger.info("Retrieved all teams successfully");
        return teamResponses;
    }

    @GetMapping("/{team_id}")
    public TeamResponse getTeamById(@PathVariable long team_id) {
        logger.info("Retrieving team with id: {}", team_id);
        Team team = teamService.getById(team_id);
        if (team == null) {
            logger.error("Team with id {} not found", team_id);
            throw new ResourceNotFoundException("Team with id " + team_id + " not found");
        }
        logger.info("Retrieved team with id {} successfully", team_id);
        return teamTransformer.convertTeamToTeamResponse(team);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllTeams() {
        logger.info("Deleting all teams");
        teamService.deleteAll();
        logger.info("All teams deleted successfully");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{team_id}")
    public ResponseEntity<HttpStatus> deleteTeam(@PathVariable long team_id) {
        logger.info("Deleting team with id: {}", team_id);
        teamService.delete(team_id);
        logger.info("Team with id {} deleted successfully", team_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("{team_id}/create")
    public ResponseEntity<HttpStatus> createTeam(@PathVariable("team_id") long team_id, @RequestBody @Valid TeamRequest teamRequest) {
        logger.info("Creating team with request: {}", teamRequest);
        Team team = teamTransformer.convertTeamRequestToTeam(teamRequest);
        Race race = raceService.getById(team_id);
        if (race == null) {
            logger.error("Race with id {} not found", team_id);
            throw new ResourceNotFoundException("Race with id " + team_id + " not found");
        }
        teamService.create(team, race);
        logger.info("Team created successfully");
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/{team_id}/{race_id}")
    public ResponseEntity<HttpStatus> updateTeam(@RequestBody @Valid TeamRequest teamRequest, @PathVariable("team_id") long team_id) {
        logger.info("Updating team with id {} and request: {}", team_id, teamRequest);
        Team newTeam = teamTransformer.convertTeamRequestToTeam(teamRequest);
        newTeam.setId(team_id);
        teamService.update(newTeam);
        logger.info("Team updated successfully");
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpStatus> handleException(Exception ex) {
        logger.error("An exception occurred: {}", ex.getMessage());
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

package com.project.races.controller;

import com.project.races.dto.pilot.PilotRequest;
import com.project.races.dto.pilot.PilotResponse;
import com.project.races.dto.pilot.PilotTransformer;
import com.project.races.model.Pilot;
import com.project.races.model.Team;
import com.project.races.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


import com.project.races.service.PilotService;


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

    @GetMapping
    public List<PilotResponse> getAllPilots(@PathVariable("team_id") long team_id) {
        logger.info("@Get: getTasks()");
        return pilotService.getAll().stream()
                .map(pilotTransformer::convertPilotToPilotResponse)
                .collect(Collectors.toList());
    }


    @DeleteMapping("/todos/{todo_id}/tasks/{task_id}/delete")
    public void delete(@PathVariable("todo_id") long todoId,
                       @PathVariable("task_id") long taskId) {
        pilotService.delete(taskId);
        logger.info("@Delete: deleteToDo(), id=" + taskId);
    }

    @PostMapping("/team/{team_id}/pilot/create")
    public ResponseEntity<HttpStatus> create(@RequestBody PilotRequest pilotRequest,
                                             @PathVariable("team_id") long team_id) {

        Team team = teamService.getById(team_id);
        Pilot pilot = pilotTransformer.convertPilotRequestToPilot(pilotRequest, team);
        pilotService.create(pilot);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

}

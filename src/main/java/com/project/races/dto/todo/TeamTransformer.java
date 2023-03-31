package com.project.races.dto.todo;

import com.project.races.dto.user.RaceResponse;
import com.project.races.model.Team;
import com.project.races.service.RaceService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TeamTransformer {
    private static final Logger logger = LoggerFactory.getLogger(TeamTransformer.class);

    private final ModelMapper modelMapper;
    private final RaceService userService;

    public TeamTransformer(ModelMapper modelMapper, RaceService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    public Team convertTeamRequestToTeam(TeamRequest teamRequest) {

        return modelMapper.map(teamRequest, Team.class);
    }

    public TeamResponse convertTeamToTeamResponse(Team team) {
        TeamResponse teamResponse = modelMapper.map(team, TeamResponse.class);
        teamResponse.setRaces(team.getRaces().stream().map(obj -> obj.getId()).collect(Collectors.toList()));
        return teamResponse;
    }

}

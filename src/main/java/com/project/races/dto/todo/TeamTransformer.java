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

    public TeamTransformer(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Team convertTeamRequestToTeam(TeamRequest teamRequest) {
        Team team = new Team();
        team.setName(teamRequest.getName());
        team.setPilots(new ArrayList<>());
        team.setRaces(new ArrayList<>());
        team.setScore(0L);
        return team;
    }

    public TeamResponse convertTeamToTeamResponse(Team team) {
        TeamResponse teamResponse = modelMapper.map(team, TeamResponse.class);
        teamResponse.setRaces(team.getRaces().stream().map(ob -> ob.getId()).collect(Collectors.toList()));
        return teamResponse;
    }

}

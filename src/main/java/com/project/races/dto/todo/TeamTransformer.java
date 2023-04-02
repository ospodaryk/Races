package com.project.races.dto.todo;

import com.project.races.model.Race;
import com.project.races.model.Team;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TeamTransformer {
    private static final Logger logger = LoggerFactory.getLogger(TeamTransformer.class);

    private final ModelMapper modelMapper;

    public TeamTransformer(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Team convertTeamRequestToTeam(TeamRequest teamRequest) {
        Team team = modelMapper.map(teamRequest, Team.class);
        team.setName(teamRequest.getName());
        team.setScore(0L);
        return team;
    }

    public TeamResponse convertTeamToTeamResponse(Team team) {
        TeamResponse teamResponse =new TeamResponse();
        teamResponse.setId(team.getId());
        teamResponse.setScore(team.getScore());
        teamResponse.setName(team.getName());
        teamResponse.setRaces(team.getRaces().stream().map(ob -> ob.getId()).collect(Collectors.toList()));
        teamResponse.setPilots(team.getPilots().stream().map(ob -> ob.getId()).collect(Collectors.toList()));
        return teamResponse;
    }

}

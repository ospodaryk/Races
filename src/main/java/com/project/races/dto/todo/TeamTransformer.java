package com.project.races.dto.todo;

import com.project.races.model.Team;
import com.project.races.service.TeamService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class TeamTransformer {
    private static final Logger logger = LoggerFactory.getLogger(TeamTransformer.class);
    static Long count = 0L;

    private final ModelMapper modelMapper;
    private final TeamService teamService;

    public TeamTransformer(ModelMapper modelMapper, TeamService teamService) {
        this.modelMapper = modelMapper;
        this.teamService = teamService;
    }

    public Team convertTeamRequestToTeam(TeamRequest teamRequest) {
        Team team = modelMapper.map(teamRequest, Team.class);
        team.setName(teamRequest.getName());
        team.setRaces(new ArrayList<>());
        team.setPilots(new ArrayList<>());
        Set<String> a = teamService.getAll().stream().map(obj -> obj.getName()).collect(Collectors.toSet());
        if (!a.contains(teamRequest.getName())) {
            count++;
        }
        team.setStaticNumber(count);

        team.setScore(0L);
        return team;
    }

    public TeamResponse convertTeamToTeamResponse(Team team) {
        TeamResponse teamResponse = new TeamResponse();
        teamResponse.setStaticNumber(team.getStaticNumber());
        teamResponse.setId(team.getId());
        teamResponse.setScore(team.getScore());
        teamResponse.setName(team.getName());
        teamResponse.setRaces(team.getRaces().stream().map(ob -> ob.getId()).collect(Collectors.toList()));
        teamResponse.setPilots(team.getPilots().stream().map(ob -> ob.getId()).collect(Collectors.toList()));
        return teamResponse;
    }

}

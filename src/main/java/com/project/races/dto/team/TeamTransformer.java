package com.project.races.dto.team;

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
        try {
            logger.info("Converting team request to team");
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
            logger.info("Team request converted to team");
            return team;
        } catch (Exception e) {
            logger.error("Error converting team request to team: {}", e.getMessage());
            throw e;
        }
    }

    public TeamResponse convertTeamToTeamResponse(Team team) {
        try {
            logger.info("Converting team to team response");
            TeamResponse teamResponse = new TeamResponse();
            teamResponse.setStaticNumber(team.getStaticNumber());
            teamResponse.setId(team.getId());
            teamResponse.setScore(team.getScore());
            teamResponse.setName(team.getName());
            teamResponse.setRaces(team.getRaces().stream().map(ob -> ob.getId()).collect(Collectors.toList()));
            teamResponse.setPilots(team.getPilots().stream().map(ob -> ob.getId()).collect(Collectors.toList()));
            logger.info("Team converted to team response");
            return teamResponse;
        } catch (Exception e) {
            logger.error("Error converting team to team response: {}", e.getMessage());
            throw e;
        }
    }
}


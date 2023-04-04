package com.project.races.service.implementation;

import com.project.races.model.Pilot;
import com.project.races.model.Race;
import com.project.races.model.Team;
import com.project.races.repository.RaceRepository;
import com.project.races.repository.TeamRepository;
import com.project.races.service.RaceService;
import com.project.races.service.TeamService;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final RaceService raceService;
    private static final Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);

    public TeamServiceImpl(TeamRepository teamRepository, RaceService raceService) {
        this.teamRepository = teamRepository;
        this.raceService = raceService;
    }

    @Override
    public Team create(Team team) {
        if (team != null) {
            logger.info("Creating new team: {}", team.getName());
            return teamRepository.save(team);
        }
        return null;
    }

    @Override
    public void create(Team team, Race race) {
        logger.info("Adding race to team: {} - {}", team.getName(), race.getName());
        team.getRaces().add(race);
        teamRepository.save(team);
    }

    @Override
    public void deleteAll() {
        logger.info("Deleting all teams");
        teamRepository.findAll().forEach(obj -> delete(obj.getId()));
    }

    @Override
    public Team findByPilots(List<Pilot> list) {
        logger.info("Finding team by pilots: {}", list);
        return teamRepository.findAll().stream().filter(obj -> obj.getPilots().equals(list)).findAny().get();

    }

    @Override
    public List<Team> findByRaceID(int raceID) {
        logger.info("Finding teams by race ID: {}", raceID);
        return raceService.getById(raceID).getTeams();
    }

    @Transactional
    @Override
    public Team getById(long id) {
        logger.info("Getting team by ID: {}", id);
        Team team = teamRepository.findById(id).orElse(null);
        if (team != null) {
            Hibernate.initialize(team.getPilots());
        }
        return team;
    }

    @Override
    public Team update(Team team) {
        if (team != null) {
            logger.info("Updating team: {}", team.getName());
            getById(team.getId());
            return teamRepository.save(team);
        }
        return null;
    }

    @Override
    public void delete(long id) {
        logger.info("Deleting team by ID: {}", id);
        Team team = getById(id);
        teamRepository.delete(team);
    }

    @Override
    public List<Team> getAll() {
        logger.info("Getting all teams");
        return teamRepository.findAll();
    }
}

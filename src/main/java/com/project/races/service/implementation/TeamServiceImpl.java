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
            logger.info("Team create success");
            return teamRepository.save(team);
        }
        logger.error("Team  cannot 'null'");
        return null;
    }

    @Override
    public void create(Team team, Race race) {
        team.getRaces().add(race);
        teamRepository.save(team);
    }

    @Override
    public void deleteAll() {
        logger.info("Delete all Teams");
        teamRepository.findAll().forEach(obj -> delete(obj.getId()));
    }

    @Override
    public Team findByPilots(List<Pilot> list) {
        return teamRepository.findAll().stream().filter(obj -> obj.getPilots().equals(list)).findAny().get();

    }

    @Override
    public List<Team> findByRaceID(int raceID) {
        return raceService.getById(raceID).getTeams();
    }

    @Transactional
    @Override
    public Team getById(long id) {
        logger.info("Read Team by ID=" + id);
        Team race = teamRepository.findById(id).orElse(null);
        if (race != null) {
            Hibernate.initialize(race.getPilots());
        }
        return race;
    }

    @Override
    public Team update(Team recipe) {
        if (recipe != null) {
            getById(recipe.getId());
            logger.info("Updated Team " + recipe);
            return teamRepository.save(recipe);
        }
        logger.error("Team to update cannot be 'null'");
        return null;
    }

    @Override
    public void delete(long id) {
        Team recipe = getById(id);
        logger.info("Delete Team by ID=" + id);
        teamRepository.delete(recipe);
    }

    @Override
    public List<Team> getAll() {
        logger.info("Get all Teams");
        return teamRepository.findAll();
    }

}

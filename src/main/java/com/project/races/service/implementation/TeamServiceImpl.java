package com.project.races.service.implementation;

import com.project.races.model.Pilot;
import com.project.races.model.Race;
import com.project.races.model.Team;
import com.project.races.repository.RaceRepository;
import com.project.races.repository.TeamRepository;
import com.project.races.service.RaceService;
import com.project.races.service.TeamService;
import jakarta.persistence.EntityNotFoundException;
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
    private final RaceRepository raceRepository;

    public TeamServiceImpl(TeamRepository teamRepository, RaceService raceService,
                           RaceRepository raceRepository) {
        this.teamRepository = teamRepository;
        this.raceService = raceService;
        this.raceRepository = raceRepository;
    }

    @Override
    public Team create(Team recipe) {
        if (recipe != null) {
            logger.info("Recipe create success");
            return teamRepository.save(recipe);
        }
        logger.error("Recipe  cannot 'null'");
        return null;
    }

    @Override
    public void create(Team team, Race race) {
        team.getRaces().add(race);
        teamRepository.save(team);
    }

    @Override
    public void deleteAll() {
        logger.info("Delete all recipes");
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
        logger.info("Read recipe by ID=" + id);
        Team race = teamRepository.findById(id).orElse(null);
        if (race != null) {
            // Initialize the lazy-loaded collection
            Hibernate.initialize(race.getPilots());
        }
        return race;
    }

    @Override
    public Team update(Team recipe) {
        if (recipe != null) {
            System.out.println("recipe != null" );

            getById(recipe.getId());
            System.out.println("Updated recipe " );

            logger.info("Updated recipe " + recipe);
            return teamRepository.save(recipe);
        }
        System.out.println(" null" );

        logger.error("Recipe to update cannot be 'null'");
        return null;
    }

    @Override
    public void delete(long id) {
        Team recipe = getById(id);
        logger.info("Delete recipe by ID=" + id);
        teamRepository.delete(recipe);
    }

    @Override
    public List<Team> getAll() {
        logger.info("Get all Recipes");
        return teamRepository.findAll();
    }

}

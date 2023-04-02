package com.project.races.service.implementation;

import com.project.races.model.Race;
import com.project.races.model.Team;
import com.project.races.repository.RaceRepository;
import com.project.races.service.RaceService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RaceServiceImpl implements RaceService {
    private final RaceRepository raceRepository;

    private static final Logger logger = LoggerFactory.getLogger(RaceServiceImpl.class);

    public RaceServiceImpl(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Override
    public Race create(Race recipe) {
        if (recipe != null) {
            logger.info("Recipe create success");
            return raceRepository.save(recipe);
        }
        logger.error("Recipe  cannot 'null'");
        return null;
    }


    @Override
    public void deleteAll() {
        logger.info("Delete all recipes");
        raceRepository.findAll().forEach(obj -> delete(obj.getId()));
    }

    @Override
    public Race findRaceByName(String name) {
        return raceRepository.findAll().stream().filter(obj -> obj.getName().equals(name)).findAny().get();
    }

    @Override
    public Race findRaceByDate(LocalDateTime localDateTime) {
        return raceRepository.findAll().stream().filter(obj -> obj.getDateOfStart().equals(localDateTime)).findAny().get();
    }

    @Transactional
    @Override
    public Race getById(long id) {
        logger.info("Read recipe by ID=" + id);
        Race race = raceRepository.findById(id).orElse(null);
        if (race != null) {
            // Initialize the lazy-loaded collection
            Hibernate.initialize(race.getTeams());
        }
        return race;
    }

    @Override
    public Race update(Race recipe) {
        if (recipe != null) {
            getById(recipe.getId());
            logger.info("Updated recipe " + recipe);
            return raceRepository.save(recipe);
        }
        logger.error("Recipe to update cannot be 'null'");
        return null;
    }

    @Override
    public void update(Race race, Team team) {
        logger.info("___________update");

        if (race != null) {
            getById(race.getId());
            logger.info("___________PREVIOS LIST" + race.getTeams());

            race.getTeams().add(team);
            logger.info("___________FINISH LIST" + race.getTeams());

            logger.info("Updated recipe " + race);
            raceRepository.save(race);
        }
        logger.error("Recipe to update cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        Race recipe = getById(id);
        logger.info("Delete recipe by ID=" + id);
        raceRepository.delete(recipe);
    }

    @Override
    public List<Race> getAll() {
        logger.info("Get all Recipes");
        return raceRepository.findAll();
    }

}

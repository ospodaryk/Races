package com.project.races.service.implementation;

import com.project.races.exception.NullEntityReferenceException;
import com.project.races.model.Race;
import com.project.races.repository.RaceRepository;
import com.project.races.service.RaceService;
import jakarta.persistence.EntityNotFoundException;
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
        throw new NullEntityReferenceException("Recipe cannot be 'null'");
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

    @Override
    public Race getById(long id) {
        logger.info("Read recipe by ID=" + id);
        return raceRepository.findById(id).orElseThrow(() -> {
            logger.error("Recipe with id " + id + " not found");
            throw new EntityNotFoundException("Recipe with id " + id + " not found");
        });
    }

    @Override
    public Race update(Race recipe) {
        if (recipe != null) {
            getById(recipe.getId());
            logger.info("Updated recipe " + recipe);
            return raceRepository.save(recipe);
        }
        logger.error("Recipe to update cannot be 'null'");
        throw new NullEntityReferenceException("Recipe to update cannot be 'null'");
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

package com.project.races.service.implementation;

import com.project.races.exception.NullEntityReferenceException;
import com.project.races.model.Pilot;
import com.project.races.model.Team;
import com.project.races.repository.TeamRepository;
import com.project.races.service.TeamService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    private static final Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team create(Team recipe) {
        if (recipe != null) {
            logger.info("Recipe create success");
            return teamRepository.save(recipe);
        }
        logger.error("Recipe  cannot 'null'");
        throw new NullEntityReferenceException("Recipe cannot be 'null'");
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
    public Team getById(long id) {
        logger.info("Read recipe by ID=" + id);
        return teamRepository.findById(id).orElseThrow(() -> {
            logger.error("Recipe with id " + id + " not found");
            throw new EntityNotFoundException("Recipe with id " + id + " not found");
        });
    }

    @Override
    public Team update(Team recipe) {
        if (recipe != null) {
            getById(recipe.getId());
            logger.info("Updated recipe " + recipe);
            return teamRepository.save(recipe);
        }
        logger.error("Recipe to update cannot be 'null'");
        throw new NullEntityReferenceException("Recipe to update cannot be 'null'");
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

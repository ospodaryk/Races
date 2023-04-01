package com.project.races.service.implementation;

import com.project.races.model.Pilot;
import com.project.races.repository.PilotRepository;
import com.project.races.service.PilotService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PilotServiceImpl implements PilotService {
    private final PilotRepository pilotRepository;

    private static final Logger logger = LoggerFactory.getLogger(PilotServiceImpl.class);

    public PilotServiceImpl(PilotRepository pilotRepository) {
        this.pilotRepository = pilotRepository;
    }

    @Override
    public Pilot create(Pilot recipe) {
        if (recipe != null) {
            logger.info("Recipe create success");
            return pilotRepository.save(recipe);
        }
        logger.error("Recipe  cannot 'null'");
        return null;
    }


    @Override
    public void deleteAll() {
        logger.info("Delete all recipes");
        pilotRepository.findAll().forEach(obj -> delete(obj.getId()));
    }

    @Override
    public List<Pilot> findByCountry(String country) {
        return pilotRepository.findAll().stream().filter(obj -> obj.getCountry().equals(country)).collect(Collectors.toList());

    }

    @Override
    public List<Pilot> findByTeam(String team) {
        return pilotRepository.findAll().stream().filter(obj -> obj.getTeam().equals(team)).collect(Collectors.toList());

    }

    @Override
    public Pilot findByNumber(Integer number) {
        return pilotRepository.findAll().stream().filter(obj -> obj.getNumber().equals(number)).findAny().get();
    }

    @Override
    public Pilot findBySurname(String surname) {
        return pilotRepository.findAll().stream().filter(obj -> obj.getSurname().equals(surname)).findAny().get();
    }


    @Override
    public Pilot getById(long id) {
        logger.info("Read recipe by ID=" + id);
        return pilotRepository.findById(id).orElseThrow(() -> {
            logger.error("Recipe with id " + id + " not found");
            throw new EntityNotFoundException("Recipe with id " + id + " not found");
        });
    }

    @Override
    public Pilot update(Pilot recipe) {
        if (recipe != null) {
            getById(recipe.getId());
            logger.info("Updated recipe " + recipe);
            return pilotRepository.save(recipe);
        }
        logger.error("Recipe to update cannot be 'null'");
        return null;
    }

    @Override
    public void delete(long id) {
        Pilot recipe = getById(id);
        logger.info("Delete recipe by ID=" + id);
        pilotRepository.delete(recipe);
    }

    @Override
    public List<Pilot> getAll() {
        logger.info("Get all Recipes");
        return pilotRepository.findAll();
    }

}

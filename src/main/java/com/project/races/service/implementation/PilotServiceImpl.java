package com.project.races.service.implementation;

import com.project.races.model.Pilot;
import com.project.races.repository.PilotRepository;
import com.project.races.service.PilotService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
    public Pilot create(Pilot pilot) {
        if (pilot != null) {
            Pilot createdPilot = pilotRepository.save(pilot);
            logger.info("Pilot created: {}", createdPilot);
            return createdPilot;
        }
        logger.error("Failed to create pilot: null parameter");
        return null;
    }


    @Override
    public void deleteAll() {
        logger.info("Deleting all pilots");
        pilotRepository.findAll().forEach(obj -> delete(obj.getId()));
    }

    @Override
    public List<Pilot> findByCountry(String country) {
        logger.info("Finding pilots by country: {}", country);
        return pilotRepository.findAll().stream().filter(obj -> obj.getCountry().equals(country)).collect(Collectors.toList());
    }

    @Override
    public List<Pilot> findByTeam(String team) {
        logger.info("Finding pilots by team: {}", team);
        return pilotRepository.findAll().stream().filter(obj -> obj.getTeam().equals(team)).collect(Collectors.toList());
    }

    @Override
    public Pilot findByNumber(Integer number) {
        logger.info("Finding pilot by number: {}", number);
        return pilotRepository.findAll().stream().filter(obj -> obj.getNumber().equals(number)).findAny().get();
    }

    @Override
    public Pilot findBySurname(String surname) {
        logger.info("Finding pilot by surname: {}", surname);
        return pilotRepository.findAll().stream().filter(obj -> obj.getSurname().equals(surname)).findAny().get();
    }

    @Transactional
    @Override
    public Pilot getById(long id) {
        logger.info("Getting pilot by ID: {}", id);
        return pilotRepository.findById(id).orElseThrow(() -> {
            logger.error("Pilot with id {} not found", id);
            throw new EntityNotFoundException("Pilot with id " + id + " not found");
        });
    }

    @Override
    public Pilot update(Pilot pilot) {
        if (pilot != null) {
            getById(pilot.getId());
            Pilot updatedPilot = pilotRepository.save(pilot);
            logger.info("Pilot updated: {}", updatedPilot);
            return updatedPilot;
        }
        logger.error("Failed to update pilot: null parameter");
        return null;
    }

    @Override
    public void delete(long id) {
        Pilot pilot = getById(id);
        logger.info("Deleting pilot by ID: {}", id);
        pilotRepository.delete(pilot);
    }

    @Override
    public List<Pilot> getAll() {
        logger.info("Getting all pilots");
        return pilotRepository.findAll();
    }

}

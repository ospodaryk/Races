package com.project.races.service.implementation;

import com.project.races.model.Pilot;
import com.project.races.model.Race;
import com.project.races.model.Team;
import com.project.races.repository.RaceRepository;
import com.project.races.repository.TeamRepository;
import com.project.races.service.RaceService;
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
    public Race create(Race race) {
        if (race != null) {
            logger.info("Race create success");
            return raceRepository.save(race);
        }
        logger.error("Race  cannot 'null'");
        return null;
    }


    @Override
    public void deleteAll() {
        logger.info("Delete all Races");
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
        logger.info("Read Race by ID=" + id);
        Race race = raceRepository.findById(id).orElse(null);
        if (race != null) {
            Hibernate.initialize(race.getTeams());
        }
        return race;
    }

    @Override
    public Team getByIdTeam(Long raceid, int teamid) {
        return getById(raceid).getTeams().stream().filter(obj -> obj.getStaticNumber() == teamid).findAny().get();
    }

    @Transactional
    public List<Pilot> getPilotsByTeam(Team team) {
        List<Pilot> pilots = team.getPilots();
        return pilots;
    }

    @Override
    public Race update(Race recipe) {
        if (recipe != null) {
            getById(recipe.getId());
            logger.info("Updated Race " + recipe);
            return raceRepository.save(recipe);
        }
        logger.error("Race to update cannot be 'null'");
        return null;
    }

    @Override
    public void delete(long id) {
        Race recipe = getById(id);
        logger.info("Delete Race by ID=" + id);
        raceRepository.delete(recipe);
    }

    @Override
    public List<Race> getAll() {
        logger.info("Get all Races");
        return raceRepository.findAll();
    }

}
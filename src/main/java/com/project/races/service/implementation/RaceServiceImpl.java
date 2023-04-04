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
            logger.info("Race created: " + race.toString());
            return raceRepository.save(race);
        }
        logger.error("Race to create cannot be 'null'");
        return null;
    }

    @Override
    public void deleteAll() {
        logger.info("Delete all races");
        raceRepository.findAll().forEach(obj -> delete(obj.getId()));
    }

    @Override
    public Race findRaceByName(String name) {
        Race race = raceRepository.findAll().stream().filter(obj -> obj.getName().equals(name)).findAny().orElse(null);
        if (race != null) {
            logger.info("Found race with name: " + name);
        } else {
            logger.error("Cannot find race with name: " + name);
        }
        return race;
    }

    @Override
    public Race findRaceByDate(LocalDateTime localDateTime) {
        Race race = raceRepository.findAll().stream().filter(obj -> obj.getDateOfStart().equals(localDateTime)).findAny().orElse(null);
        if (race != null) {
            logger.info("Found race with date of start: " + localDateTime);
        } else {
            logger.error("Cannot find race with date of start: " + localDateTime);
        }
        return race;
    }

    @Transactional
    @Override
    public Race getById(long id) {
        Race race = raceRepository.findById(id).orElse(null);
        if (race != null) {
            Hibernate.initialize(race.getTeams());
            logger.info("Read race by ID=" + id);
        } else {
            logger.error("Cannot read race by ID=" + id);
        }
        return race;
    }

    @Override
    public Team getByIdTeam(Long raceid, int teamid) {
        Team team = getById(raceid).getTeams().stream().filter(obj -> obj.getStaticNumber() == teamid).findAny().orElse(null);
        if (team != null) {
            logger.info("Found team with ID=" + teamid + " in race with ID=" + raceid);
        } else {
            logger.error("Cannot find team with ID=" + teamid + " in race with ID=" + raceid);
        }
        return team;
    }

    @Transactional
    public List<Pilot> getPilotsByTeam(Team team) {
        List<Pilot> pilots = team.getPilots();
        logger.info("Read pilots by team: " + team.toString());
        return pilots;
    }

    @Override
    public Race update(Race recipe) {
        if (recipe != null) {
            getById(recipe.getId());
            logger.info("Updated race: " + recipe.toString());
            return raceRepository.save(recipe);
        }
        logger.error("Race to update cannot be 'null'");
        return null;
    }

    @Override
    public void delete(long id) {
        Race race = getById(id);
        if (race != null) {
            logger.info("Deleted race with ID=" + id);
            raceRepository.delete(race);
        } else {
            logger.error("Cannot delete race with ID=" + id);
        }
    }

    @Override
    public List<Race> getAll() {
        logger.info("Get all races");
        return raceRepository.findAll();
    }

}
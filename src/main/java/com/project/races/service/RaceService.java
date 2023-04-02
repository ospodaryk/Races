package com.project.races.service;


import com.project.races.model.Race;
import com.project.races.model.Team;

import java.time.LocalDateTime;
import java.util.List;

public interface RaceService {

    Race create(Race race);

    Race getById(long id);

    Race update(Race race);

    void update(Race race, Team team);

    void delete(long id);

    List<Race> getAll();

    void deleteAll();

    Race findRaceByName(String name);

    Race findRaceByDate(LocalDateTime localDateTime);
}

package com.project.races.service;


import com.project.races.model.Pilot;
import com.project.races.model.Race;
import com.project.races.model.Team;

import java.util.List;

public interface TeamService {

    void create(Team team, Race race);

    List<Team> findByRaceID(int raceID);

    Team create(Team race);

    Team getById(long id);

    Team update(Team race);

    void delete(long id);

    List<Team> getAll();

    void deleteAll();

    Team findByPilots(List<Pilot> list);

}

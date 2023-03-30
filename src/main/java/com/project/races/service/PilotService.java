package com.project.races.service;


import com.project.races.model.Pilot;

import java.util.List;

public interface PilotService {

    Pilot create(Pilot pilot);

    Pilot getById(long id);

    Pilot update(Pilot pilot);

    void delete(long id);

    List<Pilot> getAll();

    void deleteAll();

    List<Pilot> findByCountry(String country);

    List<Pilot> findByTeam(String team);

    Pilot findByNumber(Integer number);

    Pilot findBySurname(String surname);
}

package com.project.races.dto.race;

import lombok.Data;

import java.util.List;

@Data
public class RaceResponse {
    private Long id;
    private String name;
    private String stadium;
    private String trackName;
    private Long numberOfLaps;
    private String dateOfStart;
    private List<Long> teamsID;
}

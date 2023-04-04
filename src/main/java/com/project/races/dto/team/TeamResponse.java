package com.project.races.dto.team;

import lombok.Data;

import java.util.List;
@Data
public class TeamResponse {
    private Long staticNumber;
    private Long id = 0L;
    private String name;
    private Long score;
    private List<Long> pilots;
    private List<Long> races;
}

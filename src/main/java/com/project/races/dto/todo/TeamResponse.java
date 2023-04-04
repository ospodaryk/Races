package com.project.races.dto.todo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

//@Value
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Setter
public class TeamResponse {
    private Long staticNumber;

    private Long id = 0L;
    private String name;
    private Long score;
    private List<Long> pilots;
    private List<Long> races;
}

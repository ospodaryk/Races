package com.project.races.dto.todo;

import com.project.races.model.Pilot;
import com.project.races.model.Race;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

//@Value
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Setter
public class TeamResponse {
    private Long id = 0L;
    private String name;

    private Long score;
    private List<Long> pilots;

    private List<Long> races;
}

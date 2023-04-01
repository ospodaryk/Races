package com.project.races.dto.user;

import com.project.races.model.Team;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Data
public class RaceResponse {

    private Long id ;

    private String name;

    private String stadium;

    private String trackName;

    private Long numberOfLaps;

    private String dateOfStart;

    private List<Long> teamsID;
}

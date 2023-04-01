package com.project.races.dto.pilot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
public class PilotResponse {
    private Long id;

    private String name;

    private String surname;

    private Long number;

    private Long score;

    private String country;

    private Long teamID;
}

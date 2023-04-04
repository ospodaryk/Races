package com.project.races.dto.pilot;

import lombok.Data;

@Data
public class PilotRequest {

    private String name;

    private String surname;

    private Long number;

    private String country;
}

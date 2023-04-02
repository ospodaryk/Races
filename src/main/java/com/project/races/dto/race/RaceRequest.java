package com.project.races.dto.race;

import lombok.Data;

@Data
public class RaceRequest {


    private String name;
    private String stadium;
    private String trackName;
    private Long numberOfLaps;

    //    @Pattern(regexp = "\\d{2}\\s\\d{2}\\s\\d{2}\\s\\d{2}\\s\\d{2}\\s\\d{4}",
//            message = "Must be in format <ss mm HH dd MM yyyy>")
    private String dateOfStart;

}

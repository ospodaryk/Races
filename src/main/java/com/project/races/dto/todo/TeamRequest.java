package com.project.races.dto.todo;

import com.project.races.model.Pilot;
import com.project.races.model.Race;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeamRequest {
    private String name;
}

package com.project.races.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Race")
@Table(name = "race")

public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "stadium")
    private String stadium;

    @NotBlank
    @Column(name = "trackName")
    private String trackName;

    @Column(name = "numberOfLaps")
    private Long numberOfLaps;

    @Column(name = "dateOfStart")
    private String dateOfStart;

    @ManyToMany
    private List<Team> teams;

}

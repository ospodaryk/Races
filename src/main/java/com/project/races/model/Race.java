package com.project.races.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity(name = "Race")
@Table(name = "race")

public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id = 0L;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "stadium")
    private String stadium;

    @NotBlank
    @Column(name = "trackName")
    private String trackName;

    @NotBlank
    @Column(name = "numberOfLaps")
    private Integer numberOfLaps;

    @NotBlank
    @Column(name = "dateOfStart")
    private LocalDateTime dateOfStart;

    @OneToMany(mappedBy = "race", cascade = CascadeType.REMOVE)
    private List<Team> teams;

}

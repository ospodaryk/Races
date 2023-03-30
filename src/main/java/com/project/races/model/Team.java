package com.project.races.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@Entity(name = "Team")
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id = 0L;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "number")
    private Integer number;

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    private List<Pilot> pilots;


    @ManyToOne
    @JoinColumn(name = "race")
    private Race race;

}

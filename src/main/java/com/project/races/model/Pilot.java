package com.project.races.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity(name = "Pilot")
@Table(name = "pilot")
public class Pilot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id = 0L;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "surname")
    private String surname;

    @NotBlank
    @Column(name = "number")
    private Integer number;

    @NotBlank
    @Column(name = "country")
    private String country;

    @ManyToOne
    @JoinColumn(name = "team")
    private Team team;

}

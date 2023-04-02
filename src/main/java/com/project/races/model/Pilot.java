package com.project.races.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    //    @NotBlank
    @Column(name = "number")
    private Long number;

    //    @NotBlank
    @Column(name = "score")
    private Long score;

    @NotBlank
    @Column(name = "country")
    private String country;

    @ManyToOne
    @JoinColumn(name = "team")
    private Team team;

}

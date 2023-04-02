package com.project.races.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

//    @NotBlank
    @Column(name = "score")
    private Long score;

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    private List<Pilot> pilots=new ArrayList<>();


    @ManyToMany
    @JoinColumn(name = "races")
    private List<Race> races=new ArrayList<>();

}

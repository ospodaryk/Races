package com.project.races.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Team")
@Table(name = "team")
public class Team  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id = 0L;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "score")
    private Long score;

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Pilot> pilots;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "race_team",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "race_id"))
    private List<Race> races;
}
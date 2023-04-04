package com.project.races.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Race")
@Table(name = "race")
public class Race  implements Serializable {
    private static final long serialVersionUID = 1L;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "race_team",
            joinColumns = @JoinColumn(name = "race_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    private List<Team> teams = new ArrayList<>();

    @Override
    public String toString() {
        return "Race{" +
                "name='" + name + '\'' +
                '}';
    }
}
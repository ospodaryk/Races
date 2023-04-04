package com.project.races.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id = 0L;
//    @NotBlank
    @Column(name = "staticNumber")
    private Long staticNumber;
    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "score")
    private Long score;
    @JsonIgnore
    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Pilot> pilots;
    @JsonIgnore

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "race_team",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "race_id"))
    private List<Race> races;

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
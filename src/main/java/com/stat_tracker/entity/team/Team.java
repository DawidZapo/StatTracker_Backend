package com.stat_tracker.entity.team;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stat_tracker.entity.player.Player;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "team_name")
    private String name;
    @OneToMany(mappedBy = "currentTeam")
    @JsonBackReference
    private List<Player> currentPlayers;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Player> getCurrentPlayers() {
        return currentPlayers;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

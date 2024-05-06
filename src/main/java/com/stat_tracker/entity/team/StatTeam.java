package com.stat_tracker.entity.team;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stat_tracker.entity.player.StatPlayer;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "stat_team")
public class StatTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
    @OneToMany(mappedBy = "statTeam")
    @JsonBackReference
    private List<StatPlayer> statPlayers;

    public Long getId() {
        return id;
    }

    public Team getTeam() {
        return team;
    }

    public List<StatPlayer> getStatPlayers() {
        return statPlayers;
    }
}

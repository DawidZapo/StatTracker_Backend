package com.stat_tracker.model.entity.team;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stat_tracker.model.entity.game.Game;
import com.stat_tracker.model.entity.player.StatPlayer;
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
    @JsonManagedReference
    private List<StatPlayer> statPlayers;

    @OneToOne(mappedBy = "home")
    @JsonIgnore
    private Game homeGame;

    @OneToOne(mappedBy = "away")
    @JsonIgnore
    private Game awayGame;

    public Long getId() {
        return id;
    }

    public Team getTeam() {
        return team;
    }

    public List<StatPlayer> getStatPlayers() {
        return statPlayers;
    }

    public Game getHomeGame() {
        return homeGame;
    }

    public Game getAwayGame() {
        return awayGame;
    }
}

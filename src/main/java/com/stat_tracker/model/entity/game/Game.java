package com.stat_tracker.model.entity.game;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stat_tracker.model.entity.plays.Play;
import com.stat_tracker.model.entity.team.StatTeam;
import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "home_id")
    private StatTeam home;

    @OneToOne
    @JoinColumn(name = "away_id")
    private StatTeam away;
    @OneToMany(mappedBy = "game")
    @JsonManagedReference
    private List<Play> plays;

    public Long getId() {
        return id;
    }

    public StatTeam getHome() {
        return home;
    }

    public StatTeam getAway() {
        return away;
    }

    public List<Play> getPlays() {
        return plays;
    }
}

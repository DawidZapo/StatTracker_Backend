package com.stat_tracker.entity.game;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stat_tracker.entity.plays.abstract_play.Play;
import com.stat_tracker.entity.team.StatTeam;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "date_time")
    private LocalDateTime localDateTime;
    @Column(name = "season")
    private String season;
    @Column(name = "is_official")
    private Boolean isOfficial;
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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public Boolean getOfficial() {
        return isOfficial;
    }

    public String getSeason() {
        return season;
    }
}

package com.stat_tracker.entity.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stat_tracker.entity.plays.abstract_play.Play;
import com.stat_tracker.entity.team.StatTeam;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stat_player")
public class StatPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
    @ManyToOne
    @JoinColumn(name = "stat_team_id")
    @JsonIgnore
    private StatTeam statTeam;
    @Column(name = "shirt_number")
    private Integer shirtNumber;
    @Column(name = "starting_five")
    private Boolean startingFive;
    @Column(name = "points")
    private Integer points;
    @Column(name = "time_on_court")
    private Long timeOnCourt;
    @OneToMany(mappedBy = "statPlayer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Play> plays = new ArrayList<>();
    @Column(name = "eval")
    private Integer eval;
    @Column(name = "plus_minus")
    private Integer plusMinus;

    public Long getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public StatTeam getStatTeam() {
        return statTeam;
    }

    public Integer getShirtNumber() {
        return shirtNumber;
    }

    public Boolean getStartingFive() {
        return startingFive;
    }

    public Integer getPoints() {
        return points;
    }

    public Long getTimeOnCourt() {
        return timeOnCourt;
    }

    public List<Play> getPlays() {
        return plays;
    }

    public Integer getEval() {
        return eval;
    }

    public Integer getPlusMinus() {
        return plusMinus;
    }

    @Override
    public String toString() {
        return "StatPlayer{" +
                "id=" + id +
                ", player=" + player +
                ", statTeam=" + statTeam +
                ", shirtNumber=" + shirtNumber +
                ", startingFive=" + startingFive +
                ", points=" + points +
                ", timeOnCourt=" + timeOnCourt +
                ", plays=" + plays +
                ", eval=" + eval +
                ", plusMinus=" + plusMinus +
                '}';
    }
}

package com.stat_tracker.entity.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stat_tracker.entity.plays.abstract_play.Play;
import com.stat_tracker.entity.stat.StatLine;
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
    @OneToMany(mappedBy = "statPlayer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Play> plays = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stat_line_id", referencedColumnName = "id")
    private StatLine statLine;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public StatTeam getStatTeam() {
        return statTeam;
    }

    public void setStatTeam(StatTeam statTeam) {
        this.statTeam = statTeam;
    }

    public Integer getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(Integer shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    public Boolean getStartingFive() {
        return startingFive;
    }

    public void setStartingFive(Boolean startingFive) {
        this.startingFive = startingFive;
    }

    public List<Play> getPlays() {
        return plays;
    }

    public void setPlays(List<Play> plays) {
        this.plays = plays;
    }

    public StatLine getStatLine() {
        return statLine;
    }

    public void setStatLine(StatLine statLine) {
        this.statLine = statLine;
    }

    @Override
    public String toString() {
        return "StatPlayer{" +
                "id=" + id +
                ", player=" + player +
                ", shirtNumber=" + shirtNumber +
                ", startingFive=" + startingFive +
                ", plays=" + plays +
                ", statLine=" + statLine +
                '}';
    }
}

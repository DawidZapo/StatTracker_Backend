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
    @Column(name = "quarter_length_min")
    private Integer quarterLengthMin;
    @Column(name = "time_remaining_ms")
    private Long timeRemainingMs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Boolean getOfficial() {
        return isOfficial;
    }

    public void setOfficial(Boolean official) {
        isOfficial = official;
    }

    public StatTeam getHome() {
        return home;
    }

    public void setHome(StatTeam home) {
        this.home = home;
    }

    public StatTeam getAway() {
        return away;
    }

    public void setAway(StatTeam away) {
        this.away = away;
    }

    public List<Play> getPlays() {
        return plays;
    }

    public void setPlays(List<Play> plays) {
        this.plays = plays;
    }

    public Integer getQuarterLengthMin() {
        return quarterLengthMin;
    }

    public void setQuarterLengthMin(Integer quarterLengthMin) {
        this.quarterLengthMin = quarterLengthMin;
    }

    public Long getTimeRemainingMs() {
        return timeRemainingMs;
    }

    public void setTimeRemainingMs(Long timeRemaining) {
        this.timeRemainingMs = timeRemaining;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", localDateTime=" + localDateTime +
                ", season='" + season + '\'' +
                ", isOfficial=" + isOfficial +
                ", home=" + home +
                ", away=" + away +
                ", plays=" + plays +
                ", quarterLengthMin=" + quarterLengthMin +
                ", timeRemainingMs=" + timeRemainingMs +
                '}';
    }
}

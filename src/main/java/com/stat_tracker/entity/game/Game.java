package com.stat_tracker.entity.game;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stat_tracker.entity.plays.abstract_play.Play;
import com.stat_tracker.entity.team.StatTeam;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @OneToOne(mappedBy = "homeGame", cascade = CascadeType.ALL)
    private StatTeam home;

    @OneToOne(mappedBy = "awayGame", cascade = CascadeType.ALL)
    private StatTeam away;

    @OneToMany(mappedBy = "game")
    @JsonManagedReference
    private List<Play> plays;
    @Column(name = "quarter_length_min")
    private Integer quarterLengthMin;
    @Column(name = "overtime_length_min")
    private Integer overtimeLengthMin;
    @Column(name = "current_quarter_time_remaining_ms")
    private Long currentQuarterTimeRemainingMs;
    @Column(name = "current_quarter")
    private Integer currentQuarter;

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

    public Integer getOvertimeLengthMin() {
        return overtimeLengthMin;
    }

    public void setOvertimeLengthMin(Integer overtimeLengthMin) {
        this.overtimeLengthMin = overtimeLengthMin;
    }

    public Long getCurrentQuarterTimeRemainingMs() {
        return currentQuarterTimeRemainingMs;
    }

    public void setCurrentQuarterTimeRemainingMs(Long currentQuarterTimeMs) {
        this.currentQuarterTimeRemainingMs = currentQuarterTimeMs;
    }

    public Integer getCurrentQuarter() {
        return currentQuarter;
    }

    public void setCurrentQuarter(Integer currentQuarter) {
        this.currentQuarter = currentQuarter;
    }

    public void addPlay(Play play){
        if(plays == null){
            plays = new ArrayList<>();
        }
        plays.add(play);
    }
}

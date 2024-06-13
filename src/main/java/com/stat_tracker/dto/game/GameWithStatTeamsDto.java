package com.stat_tracker.dto.game;

import com.stat_tracker.dto.stat_team.StatTeamDto;

public class GameWithStatTeamsDto {
    private Long id;
    private String date;
    private String time;
    private String season;
    private boolean isOfficial;
    private StatTeamDto home;
    private StatTeamDto away;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public boolean isOfficial() {
        return isOfficial;
    }

    public void setOfficial(boolean official) {
        isOfficial = official;
    }

    public StatTeamDto getHome() {
        return home;
    }

    public void setHome(StatTeamDto home) {
        this.home = home;
    }

    public StatTeamDto getAway() {
        return away;
    }

    public void setAway(StatTeamDto away) {
        this.away = away;
    }
}

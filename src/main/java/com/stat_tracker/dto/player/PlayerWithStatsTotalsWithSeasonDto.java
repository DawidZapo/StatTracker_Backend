package com.stat_tracker.dto.player;

public class PlayerWithStatsTotalsWithSeasonDto extends PlayerWithStatsTotalsDto{
    private String season;
    private String teamName;

    public PlayerWithStatsTotalsWithSeasonDto() {
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}

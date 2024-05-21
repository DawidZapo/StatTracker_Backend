package com.stat_tracker.dto.game;

import com.stat_tracker.entity.team.StatTeam;

public class GameWithStatTeamsDto {
    private Long id;
    private StatTeam home;
    private StatTeam away;

    public GameWithStatTeamsDto(Long id, StatTeam home, StatTeam away) {
        this.id = id;
        this.home = home;
        this.away = away;
    }

    public Long getId() {
        return id;
    }

    public StatTeam getHome() {
        return home;
    }

    public StatTeam getAway() {
        return away;
    }
}

package com.stat_tracker.dto.game;

import com.stat_tracker.model.entity.team.StatTeam;

public class GameWithStatTeamsDTO {
    private Long id;
    private StatTeam home;
    private StatTeam away;

    public GameWithStatTeamsDTO(Long id, StatTeam home, StatTeam away) {
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

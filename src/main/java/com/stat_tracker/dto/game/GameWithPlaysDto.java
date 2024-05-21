package com.stat_tracker.dto.game;

import com.stat_tracker.entity.plays.abstract_play.Play;

import java.util.List;

public class GameWithPlaysDto {
    private Long id;
    private List<Play> plays;

    public GameWithPlaysDto(Long id, List<Play> plays) {
        this.id = id;
        this.plays = plays;
    }

    public Long getId() {
        return id;
    }

    public List<Play> getPlays() {
        return plays;
    }
}

package com.stat_tracker.dto.game;

import com.stat_tracker.entity.plays_inheritance.Play;

import java.util.List;

public class GameWithPlaysDTO {
    private Long id;
    private List<Play> plays;

    public GameWithPlaysDTO(Long id, List<Play> plays) {
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

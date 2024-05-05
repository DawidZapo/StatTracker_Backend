package com.stat_tracker.entity.player;

import com.stat_tracker.entity.plays.Play;

import java.time.Duration;
import java.util.List;

public class StatPlayer {
    private Long id;
    private Player player;
    private Integer shirtNumber;
    private Boolean startingFive;
    private Integer points;
    private Duration timeOnCourt;
    private List<Play> plays;
    private Integer eval;
    private Integer plusMinus;
}

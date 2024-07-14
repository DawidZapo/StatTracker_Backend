package com.stat_tracker.entity.plays;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.entity.plays.abstract_play.Play;
import com.stat_tracker.entity.plays.enums.TurnoverType;
import jakarta.persistence.*;

@Entity
@Table(name = "turnover")
public class Turnover extends Play {
    @ManyToOne
    @JoinColumn(name = "steal_for_stat_player_id")
    @JsonBackReference
    private StatPlayer stealForStatPlayer;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TurnoverType type;

    public StatPlayer getStealForStatPlayer() {
        return stealForStatPlayer;
    }

    public void setStealForStatPlayer(StatPlayer turnoverForStatPlayer) {
        this.stealForStatPlayer = turnoverForStatPlayer;
    }

    public TurnoverType getType() {
        return type;
    }

    public void setType(TurnoverType type) {
        this.type = type;
    }

    @Override
    public StatPlayer getMinorPlayer() {
        return this.stealForStatPlayer;
    }
}

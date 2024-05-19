package com.stat_tracker.entity.plays;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.entity.plays.abstract_play.Play;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "steal")
public class Steal extends Play {
    @ManyToOne
    @JoinColumn(name = "stolen_from_stat_player_id")
    @JsonBackReference
    private StatPlayer stolenFromStatPlayer;

    public StatPlayer getStolenFromStatPlayer() {
        return stolenFromStatPlayer;
    }

    public void setStolenFromStatPlayer(StatPlayer stolenFromStatPlayer) {
        this.stolenFromStatPlayer = stolenFromStatPlayer;
    }
}

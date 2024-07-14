package com.stat_tracker.entity.plays;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.entity.plays.abstract_play.Play;
import jakarta.persistence.*;

@Entity
@Table(name = "block")
public class Block extends Play {
    @ManyToOne
    @JoinColumn(name = "blocked_stat_player_id")
    @JsonBackReference
    private StatPlayer blockedStatPlayer;
    @Column(name = "in_the_paint")
    private Boolean inThePaint;

    public StatPlayer getBlockedStatPlayer() {
        return blockedStatPlayer;
    }

    public void setBlockedStatPlayer(StatPlayer blockedStatPlayer) {
        this.blockedStatPlayer = blockedStatPlayer;
    }

    public Boolean getInThePaint() {
        return inThePaint;
    }

    public void setInThePaint(Boolean withinPerimeter) {
        this.inThePaint = withinPerimeter;
    }

    @Override
    public StatPlayer getMinorPlayer() {
        return this.blockedStatPlayer;
    }
}

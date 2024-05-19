package com.stat_tracker.entity.plays;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.entity.plays.abstract_play.Play;
import com.stat_tracker.entity.plays.enums.FoulType;
import jakarta.persistence.*;

@Entity
@Table(name = "foul")
public class Foul extends Play {
    @ManyToOne
    @JoinColumn(name = "foul_on_stat_player_id")
    @JsonBackReference
    private StatPlayer foulOnStatPlayer;
    @Column(name = "foul_type")
    @Enumerated(EnumType.STRING)
    private FoulType foulType;

    public StatPlayer getFoulOnStatPlayer() {
        return foulOnStatPlayer;
    }

    public void setFoulOnStatPlayer(StatPlayer foulOnStatPlayer) {
        this.foulOnStatPlayer = foulOnStatPlayer;
    }

    public FoulType getFoulType() {
        return foulType;
    }

    public void setFoulType(FoulType foulType) {
        this.foulType = foulType;
    }
}

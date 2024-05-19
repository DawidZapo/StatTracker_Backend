package com.stat_tracker.entity.plays;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.entity.plays.abstract_play.Play;
import com.stat_tracker.entity.plays.enums.AssistType;
import jakarta.persistence.*;

@Entity
@Table(name = "assist")
public class Assist extends Play {
    @ManyToOne
    @JoinColumn(name = "to_stat_player_id")
    @JsonBackReference
    private StatPlayer toStatPlayer;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AssistType type;

    public StatPlayer getToStatPlayer() {
        return toStatPlayer;
    }

    public void setToStatPlayer(StatPlayer toStatPlayer) {
        this.toStatPlayer = toStatPlayer;
    }

    public AssistType getType() {
        return type;
    }

    public void setType(AssistType assistType) {
        this.type = assistType;
    }
}

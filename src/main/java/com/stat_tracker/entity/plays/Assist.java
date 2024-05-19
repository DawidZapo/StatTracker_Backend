package com.stat_tracker.entity.plays;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.entity.plays.abstract_play.Play;
import com.stat_tracker.entity.plays.enums.AssistType;
import jakarta.persistence.*;

@Entity
@Table(name = "Assist")
public class Assist extends Play {
    @ManyToOne
    @JoinColumn(name = "to_stat_player_id")
    @JsonBackReference
    private StatPlayer toStatPlayer;
    @Column(name = "assist_type")
    @Enumerated(EnumType.STRING)
    private AssistType assistType;

    public StatPlayer getToStatPlayer() {
        return toStatPlayer;
    }

    public void setToStatPlayer(StatPlayer toStatPlayer) {
        this.toStatPlayer = toStatPlayer;
    }

    public AssistType getAssistType() {
        return assistType;
    }

    public void setAssistType(AssistType assistType) {
        this.assistType = assistType;
    }
}

package com.stat_tracker.entity.plays;

import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.entity.plays.abstract_play.Play;
import com.stat_tracker.entity.plays.enums.ViolationType;
import jakarta.persistence.*;

@Entity
@Table(name = "violation")
public class Violation extends Play {

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ViolationType type;

    public ViolationType getType() {
        return type;
    }

    public void setType(ViolationType type) {
        this.type = type;
    }

    @Override
    public StatPlayer getMinorPlayer() {
        return null;
    }
}

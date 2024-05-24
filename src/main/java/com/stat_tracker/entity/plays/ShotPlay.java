package com.stat_tracker.entity.plays;

import com.stat_tracker.entity.plays.abstract_play.Play;
import com.stat_tracker.entity.plays.enums.Contested;
import com.stat_tracker.entity.plays.enums.ShotType;
import com.stat_tracker.entity.plays.enums.Zone;
import jakarta.persistence.*;

@Entity
@Table(name = "shot_play")
public class ShotPlay extends Play {
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ShotType type;
    @Column(name = "zone")
    @Enumerated(EnumType.STRING)
    private Zone zone;
    @Column(name = "made")
    private Boolean made;
    @Column(name = "contested")
    @Enumerated(EnumType.STRING)
    private Contested contested;
    @Column(name = "worth")
    private Integer worth;

    public ShotType getType() {
        return type;
    }

    public void setType(ShotType shotType) {
        this.type = shotType;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Boolean getMade() {
        return made;
    }

    public void setMade(Boolean made) {
        this.made = made;
    }

    public Contested getContested() {
        return contested;
    }

    public void setContested(Contested contested) {
        this.contested = contested;
    }

    public Integer getWorth() {
        return worth;
    }

    public void setWorth(Integer worth) {
        this.worth = worth;
    }

    public static int calculatePossibleWorthOfShot(Zone zone) {
        return switch (zone) {
            case LEFT_CORNER, RIGHT_CORNER, LEFT_WING, RIGHT_WING, TOP_OF_THE_KEY -> 3;
            case FREE_THROW -> 1;
            default -> 2;
        };
    }

}

package com.stat_tracker.entity.plays_inheritance;

import com.stat_tracker.entity.plays_inheritance.enums.Contested;
import com.stat_tracker.entity.plays_inheritance.enums.Hand;
import com.stat_tracker.entity.plays_inheritance.enums.ShotType;
import com.stat_tracker.entity.plays_inheritance.enums.Zone;
import jakarta.persistence.*;

@Entity
@Table(name = "shot_play")
public class ShotPlay extends Play{
    @Column(name = "shot_type")
    @Enumerated(EnumType.STRING)
    private ShotType shotType;
    @Column(name = "hand")
    @Enumerated(EnumType.STRING)
    private Hand hand;
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

    public ShotType getShotType() {
        return shotType;
    }

    public void setShotType(ShotType shotType) {
        this.shotType = shotType;
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

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }
}

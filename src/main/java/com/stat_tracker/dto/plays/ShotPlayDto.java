package com.stat_tracker.dto.plays;

import com.stat_tracker.entity.plays.ShotPlay;
import com.stat_tracker.entity.plays.enums.Contested;
import com.stat_tracker.entity.plays.enums.ShotType;
import com.stat_tracker.entity.plays.enums.Zone;

public class ShotPlayDto extends PlayDto {
    private ShotType type;
    private Boolean offTheDribble;
    private Zone zone;
    private Boolean made;
    private Contested contested;
    private Integer worth;

    public ShotPlayDto(ShotPlay shotPlay){
        super(shotPlay.getId(), shotPlay.getGame().getId(),
                shotPlay.getStatPlayer().getId(),shotPlay.getDuration(), shotPlay.getComments(),
                shotPlay.getPlayType(), shotPlay.getHand());
        this.type = shotPlay.getType();
        this.zone = shotPlay.getZone();
        this.made = shotPlay.getMade();
        this.contested = shotPlay.getContested();
        this.worth = shotPlay.getWorth();
        this.offTheDribble = shotPlay.getOffTheDribble();
    }

    public ShotType getType() {
        return type;
    }

    public void setType(ShotType type) {
        this.type = type;
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

    public Boolean getOffTheDribble() {
        return offTheDribble;
    }

    public void setOffTheDribble(Boolean offTheDribble) {
        this.offTheDribble = offTheDribble;
    }
}

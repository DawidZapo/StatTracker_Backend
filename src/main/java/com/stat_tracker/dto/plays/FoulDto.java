package com.stat_tracker.dto.plays;

import com.stat_tracker.entity.plays.Foul;
import com.stat_tracker.entity.plays.enums.FoulType;

public class FoulDto extends PlayDto{
    private Long foulOnStatPlayerId;
    private FoulType foulType;

    public FoulDto(Foul foul) {
        super(foul.getId(), foul.getGame().getId(),
                foul.getStatPlayer().getId(),foul.getDuration(), foul.getComments(),
                foul.getPlayType(), foul.getHand());
        this.foulOnStatPlayerId = foul.getFoulOnStatPlayer().getId();
        this.foulType = foul.getType();
    }

    public Long getFoulOnStatPlayerId() {
        return foulOnStatPlayerId;
    }

    public void setFoulOnStatPlayerId(Long foulOnStatPlayerId) {
        this.foulOnStatPlayerId = foulOnStatPlayerId;
    }

    public FoulType getFoulType() {
        return foulType;
    }

    public void setFoulType(FoulType foulType) {
        this.foulType = foulType;
    }

    @Override
    public String toString() {
        return "FoulDto{" +
                "foulOnStatPlayerId=" + foulOnStatPlayerId +
                ", foulType=" + foulType +
                "} " + super.toString();
    }
}

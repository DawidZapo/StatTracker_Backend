package com.stat_tracker.dto.plays;

import com.stat_tracker.entity.plays.Foul;
import com.stat_tracker.entity.plays.enums.FoulType;

public class FoulDto extends PlayDto{
    private Long foulOnStatPlayerId;
    private FoulType type;

    public FoulDto() {
        super();
    }

    public FoulDto(Foul foul) {
        super(foul.getId(), foul.getGame().getId(),
                foul.getStatPlayer().getId(), foul.getStatPlayer().getPlayer().getFirstName(),
                foul.getStatPlayer().getPlayer().getLastName(), foul.getTimeRemaining(), foul.getQuarter(), foul.getOrder(), foul.getComments(),
                foul.getPlayType(), foul.getHand());
//        this.foulOnStatPlayerId = foul.getFoulOnStatPlayer().getId();
        this.foulOnStatPlayerId = (foul.getFoulOnStatPlayer() != null) ? foul.getFoulOnStatPlayer().getId() : null;

        this.type = foul.getType();
    }

    public Long getFoulOnStatPlayerId() {
        return foulOnStatPlayerId;
    }

    public void setFoulOnStatPlayerId(Long foulOnStatPlayerId) {
        this.foulOnStatPlayerId = foulOnStatPlayerId;
    }

    public FoulType getType() {
        return type;
    }

    public void setType(FoulType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "FoulDto{" +
                "foulOnStatPlayerId=" + foulOnStatPlayerId +
                ", type=" + type +
                "} " + super.toString();
    }
}

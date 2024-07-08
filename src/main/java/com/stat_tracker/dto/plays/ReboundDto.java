package com.stat_tracker.dto.plays;

import com.stat_tracker.entity.plays.Rebound;

public class ReboundDto extends PlayDto{
    private boolean isOffensive;

    public ReboundDto() {
        super();
    }

    public ReboundDto(Rebound rebound) {
        super(rebound.getId(), rebound.getGame().getId(),
                rebound.getStatPlayer().getId(),rebound.getDuration(), rebound.getComments(),
                rebound.getPlayType(), rebound.getHand());
        this.isOffensive = rebound.getOffensive();
    }

    public boolean isOffensive() {
        return isOffensive;
    }

    public void setOffensive(boolean offensive) {
        isOffensive = offensive;
    }

    @Override
    public String toString() {
        return "ReboundDto{" +
                "isOffensive=" + isOffensive +
                "} " + super.toString();
    }
}

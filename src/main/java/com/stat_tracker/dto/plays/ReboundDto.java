package com.stat_tracker.dto.plays;

import com.stat_tracker.entity.plays.Rebound;

public class ReboundDto extends PlayDto{
    private boolean offensive;

    public ReboundDto() {
        super();
    }

    public ReboundDto(Rebound rebound) {
        super(rebound.getId(), rebound.getGame().getId(),
                rebound.getStatPlayer().getId(), rebound.getStatPlayer().getPlayer().getFirstName(),
                rebound.getStatPlayer().getPlayer().getLastName(),rebound.getTimeRemaining(),rebound.getQuarter(), rebound.getComments(),
                rebound.getPlayType(), rebound.getHand());
        this.offensive = rebound.getOffensive();
    }

    public boolean isOffensive() {
        return offensive;
    }

    public void setOffensive(boolean offensive) {
        this.offensive = offensive;
    }

    @Override
    public String toString() {
        return "ReboundDto{" +
                "offensive=" + offensive +
                "} " + super.toString();
    }
}

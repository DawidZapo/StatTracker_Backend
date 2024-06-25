package com.stat_tracker.dto.plays;

import com.stat_tracker.entity.plays.Rebound;

public class ReboundDto extends PlayDto{
    private Boolean isOffensive;

    public ReboundDto(Rebound rebound) {
        super(rebound.getId(), rebound.getGame().getId(),
                rebound.getStatPlayer().getId(),rebound.getDuration(), rebound.getComments(),
                rebound.getPlayType(), rebound.getHand());
        this.isOffensive = rebound.getOffensive();
    }

    public Boolean getOffensive() {
        return isOffensive;
    }

    public void setOffensive(Boolean offensive) {
        isOffensive = offensive;
    }
}

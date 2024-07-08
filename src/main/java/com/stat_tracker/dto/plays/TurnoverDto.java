package com.stat_tracker.dto.plays;

import com.stat_tracker.entity.plays.Turnover;

public class TurnoverDto extends PlayDto{
    private Long stealForStatPlayerId;

    public TurnoverDto(Turnover turnover){
        super(turnover.getId(), turnover.getGame().getId(),
                turnover.getStatPlayer().getId(),turnover.getDuration(), turnover.getComments(),
                turnover.getPlayType(), turnover.getHand());
        this.stealForStatPlayerId = turnover.getStealForStatPlayer().getId();
    }

    public Long getStealForStatPlayerId() {
        return stealForStatPlayerId;
    }

    public void setStealForStatPlayerId(Long stealForStatPlayerId) {
        this.stealForStatPlayerId = stealForStatPlayerId;
    }

    @Override
    public String toString() {
        return "TurnoverDto{" +
                "stealForStatPlayerId=" + stealForStatPlayerId +
                "} " + super.toString();
    }
}

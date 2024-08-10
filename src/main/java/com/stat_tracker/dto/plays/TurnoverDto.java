package com.stat_tracker.dto.plays;

import com.stat_tracker.entity.plays.Turnover;
import com.stat_tracker.entity.plays.enums.TurnoverType;

public class TurnoverDto extends PlayDto{
    private Long stealForStatPlayerId;
    private TurnoverType type;

    public TurnoverDto() {
        super();
    }

    public TurnoverDto(Turnover turnover){
        super(turnover.getId(), turnover.getGame().getId(),
                turnover.getStatPlayer().getId(),turnover.getStatPlayer().getPlayer().getFirstName(),
                turnover.getStatPlayer().getPlayer().getLastName(),turnover.getTimeRemaining(), turnover.getQuarter(), turnover.getOrder(), turnover.getComments(),
                turnover.getPlayType(), turnover.getHand());
//        this.stealForStatPlayerId = turnover.getStealForStatPlayer().getId();
        this.stealForStatPlayerId = (turnover.getStealForStatPlayer() != null) ? turnover.getStealForStatPlayer().getId() : null;
        this.type = turnover.getType();
    }

    public Long getStealForStatPlayerId() {
        return stealForStatPlayerId;
    }

    public void setStealForStatPlayerId(Long stealForStatPlayerId) {
        this.stealForStatPlayerId = stealForStatPlayerId;
    }

    public TurnoverType getType() {
        return type;
    }

    public void setType(TurnoverType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TurnoverDto{" +
                "stealForStatPlayerId=" + stealForStatPlayerId +
                "} " + super.toString();
    }
}

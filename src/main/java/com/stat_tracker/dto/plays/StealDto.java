package com.stat_tracker.dto.plays;

import com.stat_tracker.entity.plays.Steal;

public class StealDto extends PlayDto {
    private Long turnoverForStatPlayerId;

    public StealDto() {
        super();
    }

    public StealDto(Steal steal){
        super(steal.getId(), steal.getGame().getId(),
                steal.getStatPlayer().getId(), steal.getStatPlayer().getPlayer().getFirstName(),
                steal.getStatPlayer().getPlayer().getLastName(),steal.getTimeRemaining(), steal.getQuarter(), steal.getOrder(), steal.getComments(),
                steal.getPlayType(), steal.getHand());
//        this.turnoverForStatPlayerId = steal.getTurnoverForStatPlayer().getId();
        this.turnoverForStatPlayerId = (steal.getTurnoverForStatPlayer() != null) ? steal.getTurnoverForStatPlayer().getId() : null;

    }

    public Long getTurnoverForStatPlayerId() {
        return turnoverForStatPlayerId;
    }

    public void setTurnoverForStatPlayerId(Long turnoverForStatPlayerId) {
        this.turnoverForStatPlayerId = turnoverForStatPlayerId;
    }

    @Override
    public String toString() {
        return "StealDto{" +
                "turnoverForStatPlayerId=" + turnoverForStatPlayerId +
                "} " + super.toString();
    }
}

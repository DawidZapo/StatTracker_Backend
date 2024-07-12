package com.stat_tracker.dto.plays;

import com.stat_tracker.entity.plays.Assist;
import com.stat_tracker.entity.plays.enums.AssistType;

public class AssistDto extends PlayDto{
    private Long toStatPlayerId;
    private AssistType type;

    public AssistDto() {
        super();
    }

    public AssistDto(Assist assist) {
        super(assist.getId(), assist.getGame().getId(),
                assist.getStatPlayer().getId(), assist.getToStatPlayer().getPlayer().getFirstName(),
                assist.getToStatPlayer().getPlayer().getLastName(),assist.getTimeRemaining(), assist.getQuarter(), assist.getComments(),
                assist.getPlayType(), assist.getHand());
//        this.toStatPlayerId = assist.getToStatPlayer().getId();
        this.toStatPlayerId = (assist.getToStatPlayer() != null) ? assist.getStatPlayer().getId() : null;
        this.type = assist.getType();
    }

    public Long getToStatPlayerId() {
        return toStatPlayerId;
    }

    public void setToStatPlayerId(Long toStatPlayerId) {
        this.toStatPlayerId = toStatPlayerId;
    }

    public AssistType getType() {
        return type;
    }

    public void setType(AssistType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AssistDto{" +
                "toStatPlayerId=" + toStatPlayerId +
                ", type=" + type +
                "} " + super.toString();
    }
}

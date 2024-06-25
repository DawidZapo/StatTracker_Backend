package com.stat_tracker.dto.plays;

import com.stat_tracker.entity.plays.Assist;
import com.stat_tracker.entity.plays.enums.AssistType;

public class AssistDto extends PlayDto{
    private Long toStatPlayerId;
    private AssistType assistType;

    public AssistDto(Assist assist) {
        super(assist.getId(), assist.getGame().getId(),
                assist.getStatPlayer().getId(),assist.getDuration(), assist.getComments(),
                assist.getPlayType(), assist.getHand());
        this.toStatPlayerId = assist.getToStatPlayer().getId();
        this.assistType = assist.getType();
    }

    public Long getToStatPlayerId() {
        return toStatPlayerId;
    }

    public void setToStatPlayerId(Long toStatPlayerId) {
        this.toStatPlayerId = toStatPlayerId;
    }

    public AssistType getAssistType() {
        return assistType;
    }

    public void setAssistType(AssistType assistType) {
        this.assistType = assistType;
    }
}

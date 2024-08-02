package com.stat_tracker.dto.plays;

import com.stat_tracker.entity.plays.Violation;
import com.stat_tracker.entity.plays.enums.ViolationType;

public class ViolationDto extends PlayDto{
    private ViolationType type;

    public ViolationDto() {
        super();
    }

    public ViolationDto(Violation violation) {
        super(violation.getId(), violation.getGame().getId(),
                violation.getStatPlayer().getId(), violation.getStatPlayer().getPlayer().getFirstName(),
                violation.getStatPlayer().getPlayer().getLastName(),violation.getTimeRemaining(),violation.getQuarter(), violation.getComments(),
                violation.getPlayType(), violation.getHand());
        this.type = violation.getType();
    }

    public ViolationType getType() {
        return type;
    }

    public void setType(ViolationType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ViolationDto{" +
                "type=" + type +
                "} " + super.toString();
    }
}

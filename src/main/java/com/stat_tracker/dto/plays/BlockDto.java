package com.stat_tracker.dto.plays;

import com.stat_tracker.entity.plays.Block;

public class BlockDto extends PlayDto{
    private Long blockedStatPlayerId;
    private Boolean withinPerimeter;

    public BlockDto(Block block) {
        super(block.getId(), block.getGame().getId(),
                block.getStatPlayer().getId(),block.getDuration(), block.getComments(),
                block.getPlayType(), block.getHand());
        this.blockedStatPlayerId = block.getBlockedStatPlayer().getId();
        this.withinPerimeter = block.getWithinPerimeter();
    }

    public Long getBlockedStatPlayerId() {
        return blockedStatPlayerId;
    }

    public void setBlockedStatPlayerId(Long blockedStatPlayerId) {
        this.blockedStatPlayerId = blockedStatPlayerId;
    }

    public Boolean getWithinPerimeter() {
        return withinPerimeter;
    }

    public void setWithinPerimeter(Boolean withinPerimeter) {
        this.withinPerimeter = withinPerimeter;
    }

    @Override
    public String toString() {
        return "BlockDto{" +
                "blockedStatPlayerId=" + blockedStatPlayerId +
                ", withinPerimeter=" + withinPerimeter +
                "} " + super.toString();
    }
}

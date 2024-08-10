package com.stat_tracker.dto.plays;

import com.stat_tracker.entity.plays.Block;

public class BlockDto extends PlayDto{
    private Long blockedStatPlayerId;
    private Boolean inThePaint;

    public BlockDto() {
        super();
    }

    public BlockDto(Block block) {
        super(block.getId(), block.getGame().getId(),
                block.getStatPlayer().getId(), block.getStatPlayer().getPlayer().getFirstName(),
                block.getStatPlayer().getPlayer().getLastName(), block.getTimeRemaining(), block.getQuarter(), block.getOrder(), block.getComments(),
                block.getPlayType(), block.getHand());
//        this.blockedStatPlayerId = block.getBlockedStatPlayer().getId();
        this.blockedStatPlayerId = (block.getBlockedStatPlayer() != null) ? block.getBlockedStatPlayer().getId() : null;
        this.inThePaint = block.getInThePaint();
    }

    public Long getBlockedStatPlayerId() {
        return blockedStatPlayerId;
    }

    public void setBlockedStatPlayerId(Long blockedStatPlayerId) {
        this.blockedStatPlayerId = blockedStatPlayerId;
    }

    public Boolean getInThePaint() {
        return inThePaint;
    }

    public void setInThePaint(Boolean inThePaint) {
        this.inThePaint = inThePaint;
    }

    @Override
    public String toString() {
        return "BlockDto{" +
                "blockedStatPlayerId=" + blockedStatPlayerId +
                ", inThePaint=" + inThePaint +
                "} " + super.toString();
    }
}

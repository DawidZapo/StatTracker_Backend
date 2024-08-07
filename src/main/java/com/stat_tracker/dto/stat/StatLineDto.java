package com.stat_tracker.dto.stat;

public class StatLineDto {
    private Long id;
    private Long timeOnCourtInMs;
    private Integer twoAttempted;
    private Integer twoMade;
    private Integer threeAttempted;
    private Integer threeMade;
    private Integer freeThrowAttempted;
    private Integer freeThrowMade;
    private Integer totalPoints;
    private Integer offRebounds;
    private Integer defRebounds;
    private Integer assists;
    private Integer fouls;
    private Integer forcedFouls;
    private Integer turnovers;
    private Integer steals;
    private Integer blocks;
    private Integer blocksReceived;
    private Integer evaluation;
    private Integer plusMinus;
    private Integer possessions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimeOnCourtInMs() {
        return timeOnCourtInMs;
    }

    public void setTimeOnCourtInMs(Long timeOnCourtInMs) {
        this.timeOnCourtInMs = timeOnCourtInMs;
    }

    public Integer getTwoAttempted() {
        return twoAttempted;
    }

    public void setTwoAttempted(Integer twoAttempted) {
        this.twoAttempted = twoAttempted;
    }

    public Integer getTwoMade() {
        return twoMade;
    }

    public void setTwoMade(Integer twoMade) {
        this.twoMade = twoMade;
    }

    public Integer getThreeAttempted() {
        return threeAttempted;
    }

    public void setThreeAttempted(Integer threeAttempted) {
        this.threeAttempted = threeAttempted;
    }

    public Integer getThreeMade() {
        return threeMade;
    }

    public void setThreeMade(Integer threeMade) {
        this.threeMade = threeMade;
    }

    public Integer getFreeThrowAttempted() {
        return freeThrowAttempted;
    }

    public void setFreeThrowAttempted(Integer freeThrowAttempted) {
        this.freeThrowAttempted = freeThrowAttempted;
    }

    public Integer getFreeThrowMade() {
        return freeThrowMade;
    }

    public void setFreeThrowMade(Integer freeThrowMade) {
        this.freeThrowMade = freeThrowMade;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Integer getOffRebounds() {
        return offRebounds;
    }

    public void setOffRebounds(Integer offRebounds) {
        this.offRebounds = offRebounds;
    }

    public Integer getDefRebounds() {
        return defRebounds;
    }

    public void setDefRebounds(Integer defRebounds) {
        this.defRebounds = defRebounds;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public Integer getFouls() {
        return fouls;
    }

    public void setFouls(Integer fouls) {
        this.fouls = fouls;
    }

    public Integer getForcedFouls() {
        return forcedFouls;
    }

    public void setForcedFouls(Integer forcedFouls) {
        this.forcedFouls = forcedFouls;
    }

    public Integer getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(Integer turnovers) {
        this.turnovers = turnovers;
    }

    public Integer getSteals() {
        return steals;
    }

    public void setSteals(Integer steals) {
        this.steals = steals;
    }

    public Integer getBlocks() {
        return blocks;
    }

    public void setBlocks(Integer blocks) {
        this.blocks = blocks;
    }

    public Integer getBlocksReceived() {
        return blocksReceived;
    }

    public void setBlocksReceived(Integer blocksReceived) {
        this.blocksReceived = blocksReceived;
    }

    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) {
        this.evaluation = evaluation;
    }

    public Integer getPlusMinus() {
        return plusMinus;
    }

    public void setPlusMinus(Integer plusMinus) {
        this.plusMinus = plusMinus;
    }

    public Integer getPossessions() {
        return possessions;
    }

    public void setPossessions(Integer possessions) {
        this.possessions = possessions;
    }

    @Override
    public String toString() {
        return "StatLineDto{" +
                "id=" + id +
                ", timeOnCourtInMs=" + timeOnCourtInMs +
                ", twoAttempted=" + twoAttempted +
                ", twoMade=" + twoMade +
                ", threeAttempted=" + threeAttempted +
                ", threeMade=" + threeMade +
                ", freeThrowAttempted=" + freeThrowAttempted +
                ", freeThrowMade=" + freeThrowMade +
                ", totalPoints=" + totalPoints +
                ", offRebounds=" + offRebounds +
                ", defRebounds=" + defRebounds +
                ", assists=" + assists +
                ", fouls=" + fouls +
                ", forcedFouls=" + forcedFouls +
                ", turnovers=" + turnovers +
                ", steals=" + steals +
                ", blocks=" + blocks +
                ", blocksReceived=" + blocksReceived +
                ", evaluation=" + evaluation +
                ", plusMinus=" + plusMinus +
                ", possessions=" + possessions +
                '}';
    }
}

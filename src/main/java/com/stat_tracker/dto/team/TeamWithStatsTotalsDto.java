package com.stat_tracker.dto.team;

public class TeamWithStatsTotalsDto {
    private Long id;
    private String name;
    private int numberOfGames = 0;
    private int totalPoints = 0;
    private int twoPointShotsAttempted = 0;
    private int twoPointShotsMade = 0;
    private int threePointShotsAttempted = 0;
    private int threePointShotsMade = 0;
    private int freeThrowsAttempted = 0;
    private int freeThrowsMade = 0;
    private int offRebounds = 0;
    private int defRebounds = 0;
    private int assists = 0;
    private int fouls = 0;
    private int forcedFouls = 0;
    private int turnOvers = 0;
    private int steals = 0;
    private int blocks = 0;
    private int blocksReceived = 0;
    private int eval = 0;
    private int possessions = 0;

    public TeamWithStatsTotalsDto() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getTwoPointShotsAttempted() {
        return twoPointShotsAttempted;
    }

    public void setTwoPointShotsAttempted(int twoPointShotsAttempted) {
        this.twoPointShotsAttempted = twoPointShotsAttempted;
    }

    public int getTwoPointShotsMade() {
        return twoPointShotsMade;
    }

    public void setTwoPointShotsMade(int twoPointShotsMade) {
        this.twoPointShotsMade = twoPointShotsMade;
    }

    public int getThreePointShotsAttempted() {
        return threePointShotsAttempted;
    }

    public void setThreePointShotsAttempted(int threePointShotsAttempted) {
        this.threePointShotsAttempted = threePointShotsAttempted;
    }

    public int getThreePointShotsMade() {
        return threePointShotsMade;
    }

    public void setThreePointShotsMade(int threePointShotsMade) {
        this.threePointShotsMade = threePointShotsMade;
    }

    public int getFreeThrowsAttempted() {
        return freeThrowsAttempted;
    }

    public void setFreeThrowsAttempted(int freeThrowsAttempted) {
        this.freeThrowsAttempted = freeThrowsAttempted;
    }

    public int getFreeThrowsMade() {
        return freeThrowsMade;
    }

    public void setFreeThrowsMade(int freeThrowsMade) {
        this.freeThrowsMade = freeThrowsMade;
    }

    public int getOffRebounds() {
        return offRebounds;
    }

    public void setOffRebounds(int offRebounds) {
        this.offRebounds = offRebounds;
    }

    public int getDefRebounds() {
        return defRebounds;
    }

    public void setDefRebounds(int defRebounds) {
        this.defRebounds = defRebounds;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getFouls() {
        return fouls;
    }

    public void setFouls(int fouls) {
        this.fouls = fouls;
    }

    public int getForcedFouls() {
        return forcedFouls;
    }

    public void setForcedFouls(int forcedFouls) {
        this.forcedFouls = forcedFouls;
    }

    public int getTurnOvers() {
        return turnOvers;
    }

    public void setTurnOvers(int turnOvers) {
        this.turnOvers = turnOvers;
    }

    public int getSteals() {
        return steals;
    }

    public void setSteals(int steals) {
        this.steals = steals;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public int getBlocksReceived() {
        return blocksReceived;
    }

    public void setBlocksReceived(int blocksReceived) {
        this.blocksReceived = blocksReceived;
    }

    public int getEval() {
        return eval;
    }

    public void setEval(int eval) {
        this.eval = eval;
    }

    public int getPossessions() {
        return possessions;
    }

    public void setPossessions(int possessions) {
        this.possessions = possessions;
    }
}

package com.stat_tracker.dto.team.helper;

public interface StatsTotals {
    Long getId();

    void setId(Long id);

    int getNumberOfGames();

    void setNumberOfGames(int numberOfGames);

    int getTotalPoints();

    void setTotalPoints(int totalPoints);

    int getTwoPointShotsAttempted();

    void setTwoPointShotsAttempted(int twoPointShotsAttempted);

    int getTwoPointShotsMade();

    void setTwoPointShotsMade(int twoPointShotsMade);

    int getThreePointShotsAttempted();

    void setThreePointShotsAttempted(int threePointShotsAttempted);

    int getThreePointShotsMade();

    void setThreePointShotsMade(int threePointShotsMade);

    int getFreeThrowsAttempted();

    void setFreeThrowsAttempted(int freeThrowsAttempted);

    int getFreeThrowsMade();

    void setFreeThrowsMade(int freeThrowsMade);

    int getOffRebounds();

    void setOffRebounds(int offRebounds);

    int getDefRebounds();

    void setDefRebounds(int defRebounds);

    int getAssists();

    void setAssists(int assists);

    int getFouls();

    void setFouls(int fouls);

    int getForcedFouls();

    void setForcedFouls(int forcedFouls);

    int getTurnOvers();

    void setTurnOvers(int turnOvers);

    int getSteals();

    void setSteals(int steals);

    int getBlocks();

    void setBlocks(int blocks);

    int getBlocksReceived();

    void setBlocksReceived(int blocksReceived);

    int getEval();

    void setEval(int eval);
    int getPossessions();
    void setPossessions(int possessions);
}

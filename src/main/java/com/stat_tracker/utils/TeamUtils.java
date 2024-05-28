package com.stat_tracker.utils;

import com.stat_tracker.dto.team.TeamWithStatsTotalsDto;
import com.stat_tracker.dto.team.helper.Record;
import com.stat_tracker.entity.stat.StatLine;
import com.stat_tracker.entity.team.StatTeam;
import com.stat_tracker.entity.team.Team;

import java.util.List;

public class TeamUtils {
    public static void updateRecord(List<Record> recordList, String statName, int order, Integer statValue, StatTeam statTeam) {
        if (statValue == null) return;

        Record currentRecord = findRecordByName(recordList, statName);

        if (currentRecord == null || statValue > currentRecord.getValue()) {
            Record newRecord = createNewRecord(statName,order,statValue,statTeam);

            recordList.remove(currentRecord);
            recordList.add(newRecord);
        }
    }

    public static Record createNewRecord(String statName, int order, double statValue, StatTeam statTeam){
        Record newRecord = new Record();
        newRecord.setOrder(order);
        newRecord.setName(statName);
        newRecord.setValue(statValue);

        if (statTeam.getHomeGame() != null) {
            populateHomeGameDetails(newRecord, statTeam);
        } else if (statTeam.getAwayGame() != null) {
            populateAwayGameDetails(newRecord, statTeam);
        } else {
            throw new RuntimeException("StatTeam does not belong to either home or away game");
        }

        return newRecord;
    }
    public static void populateHomeGameDetails(Record record, StatTeam statTeam) {
        record.setDate(statTeam.getHomeGame().getLocalDateTime().toString().split("T")[0]);
        record.setOpponent(statTeam.getHomeGame().getAway().getTeam().getName());
        record.setScore(formatScore(statTeam.getStatLine().getTotalPoints(),
                statTeam.getHomeGame().getAway().getStatLine().getTotalPoints()));
    }

    public static void populateAwayGameDetails(Record record, StatTeam statTeam) {
        record.setDate(statTeam.getAwayGame().getLocalDateTime().toString().split("T")[0]);
        record.setOpponent(statTeam.getAwayGame().getHome().getTeam().getName());
        record.setScore(formatScore(statTeam.getStatLine().getTotalPoints(),
                statTeam.getAwayGame().getHome().getStatLine().getTotalPoints()));
    }

    public static String formatScore(int homePoints, int awayPoints) {
        return homePoints + " : " + awayPoints;
    }

    public static Record findRecordByName(List<Record> list, String statName){
        for(var record : list){
            if(record.getName().equals(statName)){
                return record;
            }
        }
        return null;
    }

    public static TeamWithStatsTotalsDto createTeamWithStatsTotalsToReturn(Team team){
        TeamWithStatsTotalsDto teamToReturn = new TeamWithStatsTotalsDto();
        teamToReturn.setId(team.getId());
        teamToReturn.setName(team.getName());
        teamToReturn.setNumberOfGames(team.getStatTeams().size());
        return teamToReturn;
    }

    public static void updateTeamStats(TeamWithStatsTotalsDto team, StatLine stats) {
        team.setTotalPoints(team.getTotalPoints() + stats.getTotalPoints());
        team.setTwoPointShotsAttempted(team.getTwoPointShotsAttempted() + stats.getTwoAttempted());
        team.setTwoPointShotsMade(team.getTwoPointShotsMade() + stats.getTwoMade());
        team.setThreePointShotsAttempted(team.getThreePointShotsAttempted() + stats.getThreeAttempted());
        team.setThreePointShotsMade(team.getThreePointShotsMade() + stats.getThreeMade());
        team.setFreeThrowsAttempted(team.getFreeThrowsAttempted() + stats.getFreeThrowAttempted());
        team.setFreeThrowsMade(team.getFreeThrowsMade() + stats.getFreeThrowMade());
        team.setOffRebounds(team.getOffRebounds() + stats.getOffRebounds());
        team.setDefRebounds(team.getDefRebounds() + stats.getDefRebounds());
        team.setAssists(team.getAssists() + stats.getAssists());
        team.setFouls(team.getFouls() + stats.getFouls());
        team.setForcedFouls(team.getForcedFouls() + stats.getForcedFouls());
        team.setTurnOvers(team.getTurnOvers() + stats.getTurnovers());
        team.setSteals(team.getSteals() + stats.getSteals());
        team.setBlocks(team.getBlocks() + stats.getBlocks());
        team.setBlocksReceived(team.getBlocksReceived() + stats.getBlocksReceived());
        team.setEval(team.getEval() + stats.getEval());
        team.setPossessions(team.getPossessions() + stats.getPossessions());
    }

    public static StatLine getOpponentStats(StatTeam statTeam) {
        if (statTeam.getHomeGame() != null) {
            return statTeam.getHomeGame().getAway().getStatLine();
        } else if (statTeam.getAwayGame() != null) {
            return statTeam.getAwayGame().getHome().getStatLine();
        } else {
            throw new RuntimeException("StatTeam does not belong to either home or away game");
        }
    }
}

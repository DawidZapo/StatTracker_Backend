package com.stat_tracker.utils;

import com.stat_tracker.dto.player.PlayerWithStatsTotalsDto;
import com.stat_tracker.dto.team.TeamWithPlayerStatsTotalsDto;
import com.stat_tracker.dto.team.TeamWithStatsTotalsDto;
import com.stat_tracker.dto.team.helper.Record;
import com.stat_tracker.dto.team.helper.StatsTotals;
import com.stat_tracker.entity.player.StatPlayer;
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
        return teamToReturn;
    }

    public static void updateStatsTotals(StatsTotals item, StatLine stats) {
        item.setTotalPoints(item.getTotalPoints() + stats.getTotalPoints());
        item.setTwoPointShotsAttempted(item.getTwoPointShotsAttempted() + stats.getTwoAttempted());
        item.setTwoPointShotsMade(item.getTwoPointShotsMade() + stats.getTwoMade());
        item.setThreePointShotsAttempted(item.getThreePointShotsAttempted() + stats.getThreeAttempted());
        item.setThreePointShotsMade(item.getThreePointShotsMade() + stats.getThreeMade());
        item.setFreeThrowsAttempted(item.getFreeThrowsAttempted() + stats.getFreeThrowAttempted());
        item.setFreeThrowsMade(item.getFreeThrowsMade() + stats.getFreeThrowMade());
        item.setOffRebounds(item.getOffRebounds() + stats.getOffRebounds());
        item.setDefRebounds(item.getDefRebounds() + stats.getDefRebounds());
        item.setAssists(item.getAssists() + stats.getAssists());
        item.setFouls(item.getFouls() + stats.getFouls());
        item.setForcedFouls(item.getForcedFouls() + stats.getForcedFouls());
        item.setTurnOvers(item.getTurnOvers() + stats.getTurnovers());
        item.setSteals(item.getSteals() + stats.getSteals());
        item.setBlocks(item.getBlocks() + stats.getBlocks());
        item.setBlocksReceived(item.getBlocksReceived() + stats.getBlocksReceived());
        item.setEvaluation(item.getEvaluation() + stats.getEval());
        item.setPossessions(item.getPossessions() + stats.getPossessions());
        item.setNumberOfGames(item.getNumberOfGames() + 1);
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

    public static PlayerWithStatsTotalsDto findPlayerWithStatsTotalsInTeam(Long id, TeamWithPlayerStatsTotalsDto team) {
        return team.getPlayers().stream()
                .filter(player -> id.equals(player.getId()))
                .findFirst()
                .orElse(null);
    }

    public static PlayerWithStatsTotalsDto createPlayerWithStatsTotalsDto(StatPlayer statPlayer){
        PlayerWithStatsTotalsDto playerDto = new PlayerWithStatsTotalsDto();
        playerDto.setId(statPlayer.getPlayer().getId());
        playerDto.setFullName(statPlayer.getPlayer().getFullName());
        return playerDto;
    }
}

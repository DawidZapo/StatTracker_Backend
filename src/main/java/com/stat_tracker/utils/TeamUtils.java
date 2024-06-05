package com.stat_tracker.utils;

import com.stat_tracker.dto.player.PlayerWithStatsTotalsDto;
import com.stat_tracker.dto.team.TeamWithPlayersDto;
import com.stat_tracker.dto.team.helper.Record;
import com.stat_tracker.dto.team.totals.TeamWithPlayerStatsTotalsDto;
import com.stat_tracker.dto.team.totals.TeamWithStatsTotalsDto;
import com.stat_tracker.entity.player.Player;
import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.entity.stat.StatLine;
import com.stat_tracker.entity.team.StatTeam;
import com.stat_tracker.entity.team.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamUtils {
    public static void updateRecord(List<Record> recordList, String statName, int order, Integer statValue, StatTeam statTeam, String playerFullName) {

        updateRecord(recordList, statName, order, statValue.doubleValue(), statTeam, playerFullName);
    }
    public static void updateRecord(List<Record> recordList, String statName, int order, Double statValue, StatTeam statTeam, String playerFullName){
        if (statValue == null) return;

        Record currentRecord = findRecordByName(recordList, statName);

        if (currentRecord == null || statValue > currentRecord.getValue()) {
            Record newRecord = createNewRecord(statName,order,statValue,statTeam, playerFullName);

            recordList.remove(currentRecord);
            recordList.add(newRecord);
        }
    }


    public static Record createNewRecord(String statName, int order, double statValue, StatTeam statTeam, String playerFullName){
        Record newRecord = new Record();
        newRecord.setOrder(order);
        newRecord.setName(statName);
        newRecord.setValue(statValue);
        newRecord.setPlayerFullName(playerFullName);

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

    public static Team teamWithPlayersDtoToTeam(TeamWithPlayersDto teamWithPlayersDto){
        Team team = new Team();
        team.setId(teamWithPlayersDto.getId());
        team.setName(teamWithPlayersDto.getName());
        team.setLocation(teamWithPlayersDto.getLocation());
        team.setArena(teamWithPlayersDto.getArena());
        team.setAddress(teamWithPlayersDto.getAddress());

        List<Player> players = new ArrayList<>();
        teamWithPlayersDto.getCurrentPlayers().forEach(playerDto -> {
            Player player = PlayerUtils.playerDtoToPlayer(playerDto);
            player.setTeam(team);
            players.add(player);

        });
        team.setCurrentPlayers(players);

        return team;
    }
}

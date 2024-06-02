package com.stat_tracker.service.team;

import com.stat_tracker.dto.player.PlayerDto;
import com.stat_tracker.dto.player.PlayerWithStatsTotalsDto;
import com.stat_tracker.dto.team.TeamWithPlayersDto;
import com.stat_tracker.dto.team.helper.Record;
import com.stat_tracker.dto.team.records.TeamWithRecordsDto;
import com.stat_tracker.dto.team.totals.TeamWithPlayerStatsTotalsDto;
import com.stat_tracker.dto.team.totals.TeamWithStatsTotalsDto;
import com.stat_tracker.entity.stat.StatLine;
import com.stat_tracker.entity.team.Team;
import com.stat_tracker.repository.team.TeamRepository;
import com.stat_tracker.utils.StatsUtils;
import com.stat_tracker.utils.TeamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {
    private TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
    public List<Team> findAll(){
        return teamRepository.findAll();
    }

    public TeamWithPlayersDto getTeamDto(Long id) {
        Team team = findTeam(id);

        List<PlayerDto> playerDtos = team.getCurrentPlayers().stream()
                .map(player -> new PlayerDto(
                        player.getId(),
                        player.getFirstName(),
                        player.getLastName(),
                        player.getHeight(),
                        player.getWeight(),
                        player.getPosition(),
                        player.getBirth()))
                .collect(Collectors.toList());

        return new TeamWithPlayersDto(
                team.getId(),
                team.getName(),
                team.getLocation(),
                team.getArena(),
                team.getAddress(),
                playerDtos);
    }

    public Team findTeam(Long id){
        return teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found id: " + id));
    }

    public TeamWithStatsTotalsDto findTeamWithStatsTotalsDto(Long id){
        Team team = findTeam(id);

        TeamWithStatsTotalsDto teamToReturn = TeamUtils.createTeamWithStatsTotalsToReturn(team);

        team.getStatTeams().forEach(statTeam -> TeamUtils.updateStatsTotals(teamToReturn, statTeam.getStatLine()));

        return teamToReturn;
    }

    public TeamWithStatsTotalsDto findTeamOpponentWithStatsTotalsDto(Long id) {
        Team team = findTeam(id);

        TeamWithStatsTotalsDto teamToReturn = new TeamWithStatsTotalsDto();
        teamToReturn.setId(-1L);
        teamToReturn.setName("Opponents");

        team.getStatTeams().stream()
                .map(TeamUtils::getOpponentStats)
                .forEach(stats -> TeamUtils.updateStatsTotals(teamToReturn, stats));

        return teamToReturn;
    }

    public TeamWithRecordsDto findTeamWithRecordsDto(Long id) {
        Team team = findTeam(id);
        TeamWithRecordsDto teamWithRecordsDto = new TeamWithRecordsDto();

        List<Record> recordList = new LinkedList<>();

        for (var statTeam : team.getStatTeams()) {
            StatLine stats = statTeam.getStatLine();
            double twoPointPercentage = StatsUtils.calculatePercentage(stats.getTwoMade(), stats.getTwoAttempted());
            double threePointPercentage = StatsUtils.calculatePercentage(stats.getThreeMade(), stats.getThreeAttempted());
            double freeThrowPercentage = StatsUtils.calculatePercentage(stats.getFreeThrowMade(), stats.getFreeThrowAttempted());
            double fieldGoalPercentage = StatsUtils.calculatePercentage(stats.getTwoMade() + stats.getThreeMade(), stats.getTwoAttempted() + stats.getThreeAttempted());

            TeamUtils.updateRecord(recordList, "Attempted 2PT", 1, stats.getTwoAttempted(), statTeam, null);
            TeamUtils.updateRecord(recordList, "Made 2PT",2, stats.getTwoMade(), statTeam, null);
            TeamUtils.updateRecord(recordList, "2PT percentage", 3, twoPointPercentage, statTeam, null);
            TeamUtils.updateRecord(recordList, "Attempted 3PT", 4, stats.getThreeAttempted(), statTeam, null);
            TeamUtils.updateRecord(recordList, "Made 3PT", 5, stats.getThreeMade(), statTeam, null);
            TeamUtils.updateRecord(recordList, "3PT percentage", 6, threePointPercentage, statTeam, null);
            TeamUtils.updateRecord(recordList, "Attempted free throws",7, stats.getFreeThrowAttempted(), statTeam, null);
            TeamUtils.updateRecord(recordList, "Made free throws",8, stats.getFreeThrowMade(), statTeam, null);
            TeamUtils.updateRecord(recordList, "Free throw percentage", 9, freeThrowPercentage, statTeam, null);
            TeamUtils.updateRecord(recordList, "Attempted field goals", 10, stats.getTwoAttempted() + stats.getThreeAttempted(), statTeam, null);
            TeamUtils.updateRecord(recordList, "Made field goals", 11, stats.getTwoMade() + stats.getThreeMade(), statTeam, null);
            TeamUtils.updateRecord(recordList, "Field goal percentage", 12, fieldGoalPercentage, statTeam, null);
            TeamUtils.updateRecord(recordList, "Total Points",13, stats.getTotalPoints(), statTeam, null);
            TeamUtils.updateRecord(recordList, "Off. Rebounds",14, stats.getOffRebounds(), statTeam, null);
            TeamUtils.updateRecord(recordList, "Def. Rebounds",15, stats.getDefRebounds(), statTeam, null);
            TeamUtils.updateRecord(recordList, "Assists",16, stats.getAssists(), statTeam, null);
            TeamUtils.updateRecord(recordList, "Fouls",17, stats.getFouls(), statTeam, null);
            TeamUtils.updateRecord(recordList, "Forced Fouls",18, stats.getForcedFouls(), statTeam, null);
            TeamUtils.updateRecord(recordList, "Turnovers",19, stats.getTurnovers(), statTeam, null);
            TeamUtils.updateRecord(recordList, "Steals",20, stats.getSteals(), statTeam, null);
            TeamUtils.updateRecord(recordList, "Blocks",21, stats.getBlocks(), statTeam, null);
            TeamUtils.updateRecord(recordList, "Block Received",22, stats.getBlocksReceived(), statTeam, null);
            TeamUtils.updateRecord(recordList, "Eval",23, stats.getEval(), statTeam, null);
            TeamUtils.updateRecord(recordList, "+/-",24, stats.getPlusMinus(), statTeam, null);
            TeamUtils.updateRecord(recordList, "Possessions",25, stats.getPossessions(), statTeam, null);
        }

        recordList = recordList.stream().sorted(Comparator.comparing(Record::getOrder))
                        .collect(Collectors.toList());

        teamWithRecordsDto.setRecords(recordList);
        return teamWithRecordsDto;
    }

    public TeamWithPlayerStatsTotalsDto findTeamWithPlayerStatsTotals(Long id){
        Team team = findTeam(id);
        TeamWithPlayerStatsTotalsDto teamWithPlayerStatsTotalsDto =  new TeamWithPlayerStatsTotalsDto();

        for (var statTeam : team.getStatTeams()) {
            for (var statPlayer : statTeam.getStatPlayers()) {

                PlayerWithStatsTotalsDto playerDto = TeamUtils.findPlayerWithStatsTotalsInTeam(statPlayer.getPlayer().getId(), teamWithPlayerStatsTotalsDto);
                if (playerDto == null) {
                    playerDto = TeamUtils.createPlayerWithStatsTotalsDto(statPlayer);
                    teamWithPlayerStatsTotalsDto.addPlayer(playerDto);
                }

                StatLine statLine = statPlayer.getStatLine();
                TeamUtils.updateStatsTotals(playerDto, statLine);

                playerDto.setTimeOnCourt(playerDto.getTimeOnCourt() + statLine.getTimeOnCourtInMs());
                if(statPlayer.getStartingFive()){
                    playerDto.incrementStartingFive();
                }
            }
        }

        return teamWithPlayerStatsTotalsDto;
    }

    public TeamWithRecordsDto findTeamWithPlayerRecordsDto(Long id){
        Team team = findTeam(id);
        TeamWithRecordsDto teamWithRecordsDto = new TeamWithRecordsDto();

        List<Record> recordList = new ArrayList<>();

        for(var statTeam : team.getStatTeams()){
            for(var statPlayer : statTeam.getStatPlayers()){
                StatLine stats = statPlayer.getStatLine();
                String playerFullName = statPlayer.getPlayer().getFullName();

                double twoPointPercentage = StatsUtils.calculatePercentage(stats.getTwoMade(), stats.getTwoAttempted());
                double threePointPercentage = StatsUtils.calculatePercentage(stats.getThreeMade(), stats.getThreeAttempted());
                double freeThrowPercentage = StatsUtils.calculatePercentage(stats.getFreeThrowMade(), stats.getFreeThrowAttempted());
                double fieldGoalPercentage = StatsUtils.calculatePercentage(stats.getTwoMade() + stats.getThreeMade(), stats.getTwoAttempted() + stats.getThreeAttempted());


                TeamUtils.updateRecord(recordList, "Attempted 2PT", 1, stats.getTwoAttempted(), statTeam, playerFullName);
                TeamUtils.updateRecord(recordList, "Made 2PT",2, stats.getTwoMade(), statTeam, playerFullName);
                TeamUtils.updateRecord(recordList, "2PT percentage", 3, twoPointPercentage, statTeam, playerFullName);
                TeamUtils.updateRecord(recordList, "Attempted 3PT", 4, stats.getThreeAttempted(), statTeam, playerFullName);
                TeamUtils.updateRecord(recordList, "Made 3PT", 5, stats.getThreeMade(), statTeam, playerFullName);
                TeamUtils.updateRecord(recordList, "3PT percentage", 6, threePointPercentage, statTeam, playerFullName);
                TeamUtils.updateRecord(recordList, "Attempted free throws",7, stats.getFreeThrowAttempted(), statTeam, playerFullName);
                TeamUtils.updateRecord(recordList, "Made free throws",8, stats.getFreeThrowMade(), statTeam, playerFullName);
                TeamUtils.updateRecord(recordList, "Free throw percentage", 9, freeThrowPercentage, statTeam, playerFullName);
                TeamUtils.updateRecord(recordList, "Attempted field goals", 10, stats.getTwoAttempted() + stats.getThreeAttempted(), statTeam, playerFullName);
                TeamUtils.updateRecord(recordList, "Made field goals", 11, stats.getTwoMade() + stats.getThreeMade(), statTeam, playerFullName);
                TeamUtils.updateRecord(recordList, "Field goal percentage", 12, fieldGoalPercentage, statTeam, playerFullName);
                TeamUtils.updateRecord(recordList, "Total Points",13, stats.getTotalPoints(), statTeam, playerFullName);
                TeamUtils.updateRecord(recordList, "Off. Rebounds",14, stats.getOffRebounds(), statTeam, playerFullName);
                TeamUtils.updateRecord(recordList, "Def. Rebounds",15, stats.getDefRebounds(), statTeam, playerFullName);
                TeamUtils.updateRecord(recordList, "Assists",16, stats.getAssists(), statTeam, playerFullName);
                TeamUtils.updateRecord(recordList, "Fouls",17, stats.getFouls(), statTeam, playerFullName);
                TeamUtils.updateRecord(recordList, "Forced Fouls",18, stats.getForcedFouls(), statTeam, playerFullName);
                TeamUtils.updateRecord(recordList, "Turnovers",19, stats.getTurnovers(), statTeam, playerFullName);
                TeamUtils.updateRecord(recordList, "Steals",20, stats.getSteals(), statTeam, playerFullName);
                TeamUtils.updateRecord(recordList, "Blocks",21, stats.getBlocks(), statTeam, playerFullName);
                TeamUtils.updateRecord(recordList, "Block Received",22, stats.getBlocksReceived(), statTeam, playerFullName);
                TeamUtils.updateRecord(recordList, "Eval",23, stats.getEval(), statTeam, playerFullName);
                TeamUtils.updateRecord(recordList, "+/-",24, stats.getPlusMinus(), statTeam, playerFullName);
                TeamUtils.updateRecord(recordList, "Possessions",25, stats.getPossessions(), statTeam, playerFullName);
            }
        }
        recordList = recordList.stream().sorted(Comparator.comparing(Record::getOrder))
                .collect(Collectors.toList());
        teamWithRecordsDto.setRecords(recordList);

        return teamWithRecordsDto;
    }

    @Transactional
    public void saveTeamWithPlayersDto(TeamWithPlayersDto teamWithPlayersDto){
        Team team = TeamUtils.teamWithPlayersDtoToTeam(teamWithPlayersDto);
        teamRepository.save(team);
    }

}

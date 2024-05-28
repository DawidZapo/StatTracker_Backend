package com.stat_tracker.service.team;

import com.stat_tracker.dto.player.PlayerDto;
import com.stat_tracker.dto.player.PlayerWithStatsTotalsDto;
import com.stat_tracker.dto.team.TeamDto;
import com.stat_tracker.dto.team.TeamWithPlayerStatsTotalsDto;
import com.stat_tracker.dto.team.TeamWithRecordsDto;
import com.stat_tracker.dto.team.TeamWithStatsTotalsDto;
import com.stat_tracker.dto.team.helper.Record;
import com.stat_tracker.entity.stat.StatLine;
import com.stat_tracker.entity.team.Team;
import com.stat_tracker.repository.team.TeamRepository;
import com.stat_tracker.utils.TeamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
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

    public TeamDto getTeamDto(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found id: " + id));

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

        return new TeamDto(
                team.getId(),
                team.getName(),
                team.getLocation(),
                team.getArena(),
                team.getAddress(),
                playerDtos);
    }

    public Team findTeam(Long id){
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if(optionalTeam.isPresent()){
            return optionalTeam.get();
        }
        else{
            throw new RuntimeException("Team not found id: " + id);
        }
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
        teamToReturn.setNumberOfGames(team.getStatTeams().size());

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

            TeamUtils.updateRecord(recordList, "Attempted: 2PT", 1, stats.getTwoAttempted(), statTeam);
            TeamUtils.updateRecord(recordList, "Made: 2PT",2, stats.getTwoMade(), statTeam);
            TeamUtils.updateRecord(recordList, "Attempted: 3PT", 3, stats.getThreeAttempted(), statTeam);
            TeamUtils.updateRecord(recordList, "Made 3PT", 4, stats.getThreeMade(), statTeam);
            TeamUtils.updateRecord(recordList, "Attempted free throws",5, stats.getFreeThrowAttempted(), statTeam);
            TeamUtils.updateRecord(recordList, "Made free throws",6, stats.getFreeThrowMade(), statTeam);
            TeamUtils.updateRecord(recordList, "Total Points",7, stats.getTotalPoints(), statTeam);
            TeamUtils.updateRecord(recordList, "Off. Rebounds",8, stats.getOffRebounds(), statTeam);
            TeamUtils.updateRecord(recordList, "Def. Rebounds",9, stats.getDefRebounds(), statTeam);
            TeamUtils.updateRecord(recordList, "Assists",10, stats.getAssists(), statTeam);
            TeamUtils.updateRecord(recordList, "Fouls",11, stats.getFouls(), statTeam);
            TeamUtils.updateRecord(recordList, "Forced Fouls",12, stats.getForcedFouls(), statTeam);
            TeamUtils.updateRecord(recordList, "Turnovers",13, stats.getTurnovers(), statTeam);
            TeamUtils.updateRecord(recordList, "Steals",14, stats.getSteals(), statTeam);
            TeamUtils.updateRecord(recordList, "Blocks",15, stats.getBlocks(), statTeam);
            TeamUtils.updateRecord(recordList, "Block Received",16, stats.getBlocksReceived(), statTeam);
            TeamUtils.updateRecord(recordList, "Eval",17, stats.getEval(), statTeam);
            TeamUtils.updateRecord(recordList, "+/-",18, stats.getPlusMinus(), statTeam);
            TeamUtils.updateRecord(recordList, "Possessions",19, stats.getPossessions(), statTeam);
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
                    playerDto.incrementNumberOfGames();
                }
            }
        }

        return teamWithPlayerStatsTotalsDto;
    }



}

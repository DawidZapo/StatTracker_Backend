package com.stat_tracker.service.team;

import com.stat_tracker.dto.player.PlayerDto;
import com.stat_tracker.dto.team.TeamDto;
import com.stat_tracker.dto.team.TeamWithRecordsDto;
import com.stat_tracker.dto.team.TeamWithStatsTotalsDto;
import com.stat_tracker.dto.team.helper.Record;
import com.stat_tracker.entity.stat.StatLine;
import com.stat_tracker.entity.team.StatTeam;
import com.stat_tracker.entity.team.Team;
import com.stat_tracker.repository.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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

        TeamWithStatsTotalsDto teamToReturn = createTeamWithStatsTotalsToReturn(team);

        team.getStatTeams().forEach(statTeam -> updateTeamStats(teamToReturn, statTeam.getStatLine()));

        return teamToReturn;
    }

    public TeamWithStatsTotalsDto findTeamOpponentWithStatsTotalsDto(Long id) {
        Team team = findTeam(id);

        TeamWithStatsTotalsDto teamToReturn = new TeamWithStatsTotalsDto();
        teamToReturn.setId(-1L);
        teamToReturn.setName("Opponents");
        teamToReturn.setNumberOfGames(team.getStatTeams().size());

        team.getStatTeams().stream()
                .map(this::getOpponentStats)
                .forEach(stats -> updateTeamStats(teamToReturn, stats));

        return teamToReturn;
    }

    public TeamWithRecordsDto findTeamWithRecordsDto(Long id) {
        Team team = findTeam(id);
        TeamWithRecordsDto teamWithRecordsDto = new TeamWithRecordsDto();

        Map<String, Record> recordMap = new HashMap<>();

        for (var statTeam : team.getStatTeams()) {
            StatLine stats = statTeam.getStatLine();

            updateRecord(recordMap, "two_attempted", stats.getTwoAttempted(), statTeam);
            updateRecord(recordMap, "two_made", stats.getTwoMade(), statTeam);
            updateRecord(recordMap, "three_attempted", stats.getThreeAttempted(), statTeam);
            updateRecord(recordMap, "three_made", stats.getThreeMade(), statTeam);
            updateRecord(recordMap, "free_throw_attempted", stats.getFreeThrowAttempted(), statTeam);
            updateRecord(recordMap, "free_throw_made", stats.getFreeThrowMade(), statTeam);
            updateRecord(recordMap, "total_points", stats.getTotalPoints(), statTeam);
            updateRecord(recordMap, "off_rebounds", stats.getOffRebounds(), statTeam);
            updateRecord(recordMap, "def_rebounds", stats.getDefRebounds(), statTeam);
            updateRecord(recordMap, "assists", stats.getAssists(), statTeam);
            updateRecord(recordMap, "fouls", stats.getFouls(), statTeam);
            updateRecord(recordMap, "forced_fouls", stats.getForcedFouls(), statTeam);
            updateRecord(recordMap, "turnovers", stats.getTurnovers(), statTeam);
            updateRecord(recordMap, "steals", stats.getSteals(), statTeam);
            updateRecord(recordMap, "blocks", stats.getBlocks(), statTeam);
            updateRecord(recordMap, "blocks_received", stats.getBlocksReceived(), statTeam);
            updateRecord(recordMap, "eval", stats.getEval(), statTeam);
            updateRecord(recordMap, "plus_minus", stats.getPlusMinus(), statTeam);
            updateRecord(recordMap, "possessions", stats.getPossessions(), statTeam);
        }

        teamWithRecordsDto.setRecords(recordMap);
        return teamWithRecordsDto;
    }

    private void updateRecord(Map<String, Record> recordMap, String statName, Integer statValue, StatTeam statTeam) {
        if (statValue == null) return;

        Record currentRecord = recordMap.get(statName);
        if (currentRecord == null || statValue > currentRecord.getValue()) {
            Record newRecord = new Record();
            newRecord.setName(statName);
            newRecord.setValue(statValue.doubleValue());
            if(statTeam.getHomeGame() != null){
                newRecord.setDate(statTeam.getHomeGame().getLocalDateTime().toString());
                newRecord.setOpponent(statTeam.getHomeGame().getAway().getTeam().getName());
                newRecord.setScore(statTeam.getStatLine().getTotalPoints() + " : "
                + statTeam.getHomeGame().getAway().getStatLine().getTotalPoints());
            }
            else if(statTeam.getAwayGame() != null){
                newRecord.setDate(statTeam.getAwayGame().getLocalDateTime().toString());
                newRecord.setOpponent(statTeam.getAwayGame().getHome().getTeam().getName());
                newRecord.setScore(statTeam.getStatLine().getTotalPoints() + " : "
                        + statTeam.getAwayGame().getHome().getStatLine().getTotalPoints());
            }
            else{
                throw new RuntimeException("StatTeam does not belong to either home or away game");
            }

            recordMap.put(statName, newRecord);
        }
    }

    private TeamWithStatsTotalsDto createTeamWithStatsTotalsToReturn(Team team){
        TeamWithStatsTotalsDto teamToReturn = new TeamWithStatsTotalsDto();
        teamToReturn.setId(team.getId());
        teamToReturn.setName(team.getName());
        teamToReturn.setNumberOfGames(team.getStatTeams().size());
        return teamToReturn;
    }

    private void updateTeamStats(TeamWithStatsTotalsDto team, StatLine stats) {
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

    private StatLine getOpponentStats(StatTeam statTeam) {
        if (statTeam.getHomeGame() != null) {
            return statTeam.getHomeGame().getAway().getStatLine();
        } else if (statTeam.getAwayGame() != null) {
            return statTeam.getAwayGame().getHome().getStatLine();
        } else {
            throw new RuntimeException("StatTeam does not belong to either home or away game");
        }
    }

}

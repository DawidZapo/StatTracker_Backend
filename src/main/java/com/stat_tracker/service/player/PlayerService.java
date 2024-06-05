package com.stat_tracker.service.player;

import com.stat_tracker.dto.player.PlayerDto;
import com.stat_tracker.dto.player.PlayerWithTeamDto;
import com.stat_tracker.dto.team.helper.Record;
import com.stat_tracker.entity.player.Player;
import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.entity.stat.StatLine;
import com.stat_tracker.repository.player.PlayerRepository;
import com.stat_tracker.utils.PlayerUtils;
import com.stat_tracker.utils.StatsUtils;
import com.stat_tracker.utils.TeamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player findPlayer(Long id){
        return playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found id: " + id));
    }
    public List<PlayerWithTeamDto> findALlPlayersWithTeamDto(){
        List<Player> players = playerRepository.findAll();
        List<PlayerWithTeamDto> playerWithTeamDtos = new ArrayList<>();

        players.forEach(player -> playerWithTeamDtos.add(PlayerUtils.createPlayerWithTeamDto(player)));

        return playerWithTeamDtos;
    }
    public Player savePlayer(Player player){
        return playerRepository.save(player);
    }
    public void removePlayerFromTeam(PlayerDto playerDto){
        Player player = PlayerUtils.playerDtoToPlayer(playerDto);
        player.setCurrentTeam(null);
        playerRepository.save(player);
    }

    public PlayerWithTeamDto findPlayerWithTeamDto(Long id){
        Player player = findPlayer(id);

        return PlayerUtils.createPlayerWithTeamDto(player);
    }

    public List<Record> findPlayerRecords(Long id, String season){
        Player player = findPlayer(id);
        List<StatPlayer> filteredStatPlayers = StatsUtils.getFilteredStatPlayer(player.getStatPlayers(), season);

        List<Record> recordList = new ArrayList<>();

        for (var statPlayer : filteredStatPlayers) {
            StatLine stats = statPlayer.getStatLine();
            double twoPointPercentage = StatsUtils.calculatePercentage(stats.getTwoMade(), stats.getTwoAttempted());
            double threePointPercentage = StatsUtils.calculatePercentage(stats.getThreeMade(), stats.getThreeAttempted());
            double freeThrowPercentage = StatsUtils.calculatePercentage(stats.getFreeThrowMade(), stats.getFreeThrowAttempted());
            double fieldGoalPercentage = StatsUtils.calculatePercentage(stats.getTwoMade() + stats.getThreeMade(), stats.getTwoAttempted() + stats.getThreeAttempted());
            String playerFullName = statPlayer.getPlayer().getFullName();

            TeamUtils.updateRecord(recordList, "Attempted 2PT", 1, stats.getTwoAttempted(), statPlayer.getStatTeam(), playerFullName);
            TeamUtils.updateRecord(recordList, "Made 2PT",2, stats.getTwoMade(), statPlayer.getStatTeam(), playerFullName);
            TeamUtils.updateRecord(recordList, "2PT percentage", 3, twoPointPercentage, statPlayer.getStatTeam(), playerFullName);
            TeamUtils.updateRecord(recordList, "Attempted 3PT", 4, stats.getThreeAttempted(), statPlayer.getStatTeam(), playerFullName);
            TeamUtils.updateRecord(recordList, "Made 3PT", 5, stats.getThreeMade(), statPlayer.getStatTeam(), playerFullName);
            TeamUtils.updateRecord(recordList, "3PT percentage", 6, threePointPercentage, statPlayer.getStatTeam(), playerFullName);
            TeamUtils.updateRecord(recordList, "Attempted free throws",7, stats.getFreeThrowAttempted(), statPlayer.getStatTeam(), playerFullName);
            TeamUtils.updateRecord(recordList, "Made free throws",8, stats.getFreeThrowMade(), statPlayer.getStatTeam(), playerFullName);
            TeamUtils.updateRecord(recordList, "Free throw percentage", 9, freeThrowPercentage, statPlayer.getStatTeam(), playerFullName);
            TeamUtils.updateRecord(recordList, "Attempted field goals", 10, stats.getTwoAttempted() + stats.getThreeAttempted(), statPlayer.getStatTeam(), playerFullName);
            TeamUtils.updateRecord(recordList, "Made field goals", 11, stats.getTwoMade() + stats.getThreeMade(), statPlayer.getStatTeam(), playerFullName);
            TeamUtils.updateRecord(recordList, "Field goal percentage", 12, fieldGoalPercentage, statPlayer.getStatTeam(), playerFullName);
            TeamUtils.updateRecord(recordList, "Total Points",13, stats.getTotalPoints(), statPlayer.getStatTeam(), playerFullName);
            TeamUtils.updateRecord(recordList, "Off. Rebounds",14, stats.getOffRebounds(), statPlayer.getStatTeam(), playerFullName);
            TeamUtils.updateRecord(recordList, "Def. Rebounds",15, stats.getDefRebounds(), statPlayer.getStatTeam(), playerFullName);
            TeamUtils.updateRecord(recordList, "Assists",16, stats.getAssists(), statPlayer.getStatTeam(), playerFullName);
            TeamUtils.updateRecord(recordList, "Fouls",17, stats.getFouls(), statPlayer.getStatTeam(), playerFullName);
            TeamUtils.updateRecord(recordList, "Forced Fouls",18, stats.getForcedFouls(), statPlayer.getStatTeam(), playerFullName);
            TeamUtils.updateRecord(recordList, "Turnovers",19, stats.getTurnovers(), statPlayer.getStatTeam(), playerFullName);
            TeamUtils.updateRecord(recordList, "Steals",20, stats.getSteals(), statPlayer.getStatTeam(), playerFullName);
            TeamUtils.updateRecord(recordList, "Blocks",21, stats.getBlocks(), statPlayer.getStatTeam(), playerFullName);
            TeamUtils.updateRecord(recordList, "Block Received",22, stats.getBlocksReceived(), statPlayer.getStatTeam(), playerFullName);
            TeamUtils.updateRecord(recordList, "Eval",23, stats.getEval(), statPlayer.getStatTeam(), playerFullName);
            TeamUtils.updateRecord(recordList, "+/-",24, stats.getPlusMinus(), statPlayer.getStatTeam(), playerFullName);
            TeamUtils.updateRecord(recordList, "Possessions",25, stats.getPossessions(), statPlayer.getStatTeam(), playerFullName);
        }

        recordList = recordList.stream().sorted(Comparator.comparing(Record::getOrder))
                .collect(Collectors.toList());



        return recordList;
    }
}

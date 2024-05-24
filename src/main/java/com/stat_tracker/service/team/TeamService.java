package com.stat_tracker.service.team;

import com.stat_tracker.dto.player.PlayerDto;
import com.stat_tracker.dto.team.TeamDto;
import com.stat_tracker.dto.team.TeamWithStatsDto;
import com.stat_tracker.entity.plays.*;
import com.stat_tracker.entity.plays.enums.Contested;
import com.stat_tracker.entity.team.Team;
import com.stat_tracker.repository.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public TeamWithStatsDto findTeamWithStatsDto(Long id){
        Team team = findTeam(id);

        TeamWithStatsDto teamWithStatsDto = new TeamWithStatsDto();
        teamWithStatsDto.setId(team.getId());
        teamWithStatsDto.setName(team.getName());
        teamWithStatsDto.setNumberOfGames(team.getStatTeams().size());

        for(var statTeam : team.getStatTeams()){
            for(var player : statTeam.getStatPlayers()){
                for(var play : player.getPlays()){
                    if(play instanceof Assist){
                        teamWithStatsDto.setAssists(teamWithStatsDto.getAssists() + 1);
                    }
                    else if(play instanceof Block){
                        teamWithStatsDto.setBlocks(teamWithStatsDto.getBlocks() + 1);
                    }
                    else if(play instanceof Foul){
                        teamWithStatsDto.setFouls(teamWithStatsDto.getFouls() + 1);
                    }
                    else if(play instanceof Rebound){
                        Rebound rebound = (Rebound) play;
                        if(rebound.getOffensive()){
                            teamWithStatsDto.setOffRebounds(teamWithStatsDto.getOffRebounds() + 1);
                        }
                        else{
                            teamWithStatsDto.setDefRebounds(teamWithStatsDto.getDefRebounds() + 1);
                        }
                    }
                    else if(play instanceof ShotPlay){
                        ShotPlay shotPlay = (ShotPlay) play;
                        switch (ShotPlay.calculatePossibleWorthOfShot(shotPlay.getZone())){
                            case 1 -> {
                                teamWithStatsDto.setFreeThrowsAttempted(teamWithStatsDto.getFreeThrowsAttempted() + 1);
                                if(shotPlay.getMade()){
                                    teamWithStatsDto.setFreeThrowsMade(teamWithStatsDto.getFreeThrowsMade() + 1);
                                }
                            }
                            case 2 -> {
                                teamWithStatsDto.setTwoPointShotsAttempted(teamWithStatsDto.getTwoPointShotsAttempted() + 1);
                                if(shotPlay.getMade()){
                                    teamWithStatsDto.setTwoPointShotsMade(teamWithStatsDto.getTwoPointShotsMade() + 1);
                                }
                                else if(shotPlay.getContested().equals(Contested.BLOCKED)){
                                    teamWithStatsDto.setBlocksReceived(teamWithStatsDto.getBlocksReceived() + 1);
                                }
                            }
                            case 3 -> {
                                teamWithStatsDto.setThreePointShotsAttempted(teamWithStatsDto.getThreePointShotsAttempted() + 1);
                                if(shotPlay.getMade()){
                                    teamWithStatsDto.setThreePointShotsMade(teamWithStatsDto.getThreePointShotsMade() + 1);
                                }
                                else if(shotPlay.getContested().equals(Contested.BLOCKED)){
                                    teamWithStatsDto.setBlocksReceived(teamWithStatsDto.getBlocksReceived() + 1);
                                }
                            }
                            default -> {
                                throw new RuntimeException("Unknown worth of shot play: " + shotPlay.getWorth());
                            }
                        }
                    }
                    else if(play instanceof Steal){
                        teamWithStatsDto.setSteals(teamWithStatsDto.getSteals() + 1);
                    }
                    else if(play instanceof Turnover){
                        teamWithStatsDto.setTurnOvers(teamWithStatsDto.getTurnOvers() + 1);
                    }
                    else{
                        throw new RuntimeException("Unknown play type:  " + play.getClass().getSimpleName());
                    }
                }

                teamWithStatsDto.setEval(teamWithStatsDto.getEval() + player.getEval());
            }

            for(var score : statTeam.getScores()){
                teamWithStatsDto.setTotalPoints(teamWithStatsDto.getTotalPoints() + score.getWorth());
            }
        }

        return teamWithStatsDto;
    }

}

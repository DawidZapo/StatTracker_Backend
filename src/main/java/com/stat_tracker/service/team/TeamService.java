package com.stat_tracker.service.team;

import com.stat_tracker.dto.player.PlayerDto;
import com.stat_tracker.dto.team.TeamDto;
import com.stat_tracker.dto.team.TeamWithStatsTotalsDto;
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
    // function to be deleted, StatLine class introduced
    public TeamWithStatsTotalsDto findTeamWithStatTotalsDto(Long id){
        Team team = findTeam(id);

        TeamWithStatsTotalsDto teamWithStatsTotalsDto = new TeamWithStatsTotalsDto();
        teamWithStatsTotalsDto.setId(team.getId());
        teamWithStatsTotalsDto.setName(team.getName());
        teamWithStatsTotalsDto.setNumberOfGames(team.getStatTeams().size());

        for(var statTeam : team.getStatTeams()){
            for(var player : statTeam.getStatPlayers()){
                for(var play : player.getPlays()){
                    if(play instanceof Assist){
                        teamWithStatsTotalsDto.setAssists(teamWithStatsTotalsDto.getAssists() + 1);
                    }
                    else if(play instanceof Block){
                        teamWithStatsTotalsDto.setBlocks(teamWithStatsTotalsDto.getBlocks() + 1);
                    }
                    else if(play instanceof Foul){
                        teamWithStatsTotalsDto.setFouls(teamWithStatsTotalsDto.getFouls() + 1);
                    }
                    else if(play instanceof Rebound){
                        Rebound rebound = (Rebound) play;
                        if(rebound.getOffensive()){
                            teamWithStatsTotalsDto.setOffRebounds(teamWithStatsTotalsDto.getOffRebounds() + 1);
                        }
                        else{
                            teamWithStatsTotalsDto.setDefRebounds(teamWithStatsTotalsDto.getDefRebounds() + 1);
                        }
                    }
                    else if(play instanceof ShotPlay){
                        ShotPlay shotPlay = (ShotPlay) play;
                        switch (ShotPlay.calculatePossibleWorthOfShot(shotPlay.getZone())){
                            case 1 -> {
                                teamWithStatsTotalsDto.setFreeThrowsAttempted(teamWithStatsTotalsDto.getFreeThrowsAttempted() + 1);
                                if(shotPlay.getMade()){
                                    teamWithStatsTotalsDto.setFreeThrowsMade(teamWithStatsTotalsDto.getFreeThrowsMade() + 1);
                                }
                            }
                            case 2 -> {
                                teamWithStatsTotalsDto.setTwoPointShotsAttempted(teamWithStatsTotalsDto.getTwoPointShotsAttempted() + 1);
                                if(shotPlay.getMade()){
                                    teamWithStatsTotalsDto.setTwoPointShotsMade(teamWithStatsTotalsDto.getTwoPointShotsMade() + 1);
                                }
                                else if(shotPlay.getContested().equals(Contested.BLOCKED)){
                                    teamWithStatsTotalsDto.setBlocksReceived(teamWithStatsTotalsDto.getBlocksReceived() + 1);
                                }
                            }
                            case 3 -> {
                                teamWithStatsTotalsDto.setThreePointShotsAttempted(teamWithStatsTotalsDto.getThreePointShotsAttempted() + 1);
                                if(shotPlay.getMade()){
                                    teamWithStatsTotalsDto.setThreePointShotsMade(teamWithStatsTotalsDto.getThreePointShotsMade() + 1);
                                }
                                else if(shotPlay.getContested().equals(Contested.BLOCKED)){
                                    teamWithStatsTotalsDto.setBlocksReceived(teamWithStatsTotalsDto.getBlocksReceived() + 1);
                                }
                            }
                            default -> {
                                throw new RuntimeException("Unknown worth of shot play: " + shotPlay.getWorth());
                            }
                        }
                    }
                    else if(play instanceof Steal){
                        teamWithStatsTotalsDto.setSteals(teamWithStatsTotalsDto.getSteals() + 1);
                    }
                    else if(play instanceof Turnover){
                        teamWithStatsTotalsDto.setTurnOvers(teamWithStatsTotalsDto.getTurnOvers() + 1);
                    }
                    else{
                        throw new RuntimeException("Unknown play type:  " + play.getClass().getSimpleName());
                    }
                }

//                teamWithStatsTotalsDto.setEval(teamWithStatsTotalsDto.getEval() + player.getEval());
            }

            for(var score : statTeam.getScores()){
                teamWithStatsTotalsDto.setTotalPoints(teamWithStatsTotalsDto.getTotalPoints() + score.getWorth());
            }
        }

        return teamWithStatsTotalsDto;
    }

}

package com.stat_tracker.service.team;

import com.stat_tracker.dto.player.PlayerDto;
import com.stat_tracker.dto.team.TeamDto;
import com.stat_tracker.dto.team.TeamWithStatsTotalsDto;
import com.stat_tracker.entity.stat.StatLine;
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

    public TeamWithStatsTotalsDto findTeamWithStatsTotalsDto(Long id){
        Team team = findTeam(id);

        TeamWithStatsTotalsDto teamToReturn = createTeamWithStatsTotalsToReturn(team);

        team.getStatTeams().forEach(statTeam -> updateTeamStats(teamToReturn, statTeam.getStatLine()));

        return teamToReturn;
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

}

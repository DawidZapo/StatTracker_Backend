package com.stat_tracker.service.team;

import com.stat_tracker.dto.player.PlayerDto;
import com.stat_tracker.dto.team.TeamDto;
import com.stat_tracker.entity.team.Team;
import com.stat_tracker.repository.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public TeamDto getTeamDto(Long id){
        Optional<Team> teamOptional = teamRepository.findById(id);
        if(teamOptional.isPresent()){
            Team team = teamOptional.get();

            List<PlayerDto> playerDtos = new ArrayList<>();
            for(var player : team.getCurrentPlayers()){
                playerDtos.add(new PlayerDto(player.getId(),player.getFirstName(),player.getLastName(), player.getHeight(),player.getWeight(), player.getPosition(),player.getBirth()));
            }

            return new TeamDto(team.getId(),team.getName(),team.getLocation(),team.getArena(),team.getAddress(),playerDtos);
        }
        else{
            throw new RuntimeException("Team not found id: " + id);
        }
    }
}

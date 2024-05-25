package com.stat_tracker.rest_controller.team;

import com.stat_tracker.dto.team.TeamDto;
import com.stat_tracker.dto.team.TeamWithStatsTotalsDto;
import com.stat_tracker.entity.team.Team;
import com.stat_tracker.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class TeamController {
    private TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }
    @GetMapping("/team/all")
    public ResponseEntity<List<Team>> getTeams(){
        return ResponseEntity.ok(teamService.findAll());
    }

    @GetMapping("/team/{id}")
    public ResponseEntity<TeamDto> getTeamDto(@PathVariable Long id){
        return ResponseEntity.ok(teamService.getTeamDto(id));
    }

    @GetMapping("/team/totals/{id}")
    public ResponseEntity<TeamWithStatsTotalsDto> getTeamWithStatsDto(@PathVariable("id") Long id){
        return ResponseEntity.ok(teamService.findTeamWithStatTotalsDto(id));
    }
}

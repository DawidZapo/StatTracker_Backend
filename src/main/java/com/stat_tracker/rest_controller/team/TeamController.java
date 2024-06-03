package com.stat_tracker.rest_controller.team;

import com.stat_tracker.dto.team.TeamWithPlayersDto;
import com.stat_tracker.dto.team.records.TeamWithRecordsDto;
import com.stat_tracker.dto.team.totals.TeamWithPlayerStatsTotalsDto;
import com.stat_tracker.dto.team.totals.TeamWithStatsTotalsDto;
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
    public ResponseEntity<TeamWithPlayersDto> getTeamDto(@PathVariable Long id){
        return ResponseEntity.ok(teamService.getTeamDto(id));
    }

    @GetMapping("/team/totals/{id}/{season}")
    public ResponseEntity<TeamWithStatsTotalsDto> getTeamWithStatsTotalsDto(@PathVariable("id") Long id, @PathVariable("season") String season){
        return ResponseEntity.ok(teamService.findTeamWithStatsTotalsDto(id, season));
    }

    @GetMapping("/team/opponent_totals/{id}/{season}")
    public ResponseEntity<TeamWithStatsTotalsDto> getTeamOpponentWithStatsTotalsDto(@PathVariable("id") Long id, @PathVariable("season") String season){
        return ResponseEntity.ok(teamService.findTeamOpponentWithStatsTotalsDto(id, season));
    }

    @GetMapping("/team/records/{id}/{season}")
    public ResponseEntity<TeamWithRecordsDto> getTeamRecordsDto(@PathVariable("id") Long id, @PathVariable("season") String season){
        return ResponseEntity.ok(teamService.findTeamWithRecordsDto(id,season));
    }

    @GetMapping("/team/player_totals/{id}/{season}")
    public ResponseEntity<TeamWithPlayerStatsTotalsDto> getTeamWithPlayerStatsTotals(@PathVariable("id") Long id, @PathVariable("season") String season){
        return ResponseEntity.ok(teamService.findTeamWithPlayerStatsTotals(id, season));
    }

    @GetMapping("/team/player_records/{id}/{season}")
    public ResponseEntity<TeamWithRecordsDto> getTeamWithPlayerRecords(@PathVariable("id") Long id,@PathVariable("season") String season){
        return ResponseEntity.ok(teamService.findTeamWithPlayerRecordsDto(id, season));
    }

    @PostMapping("/team/save")
    public ResponseEntity<String> saveTeamWithPlayersDto(@RequestBody TeamWithPlayersDto teamWithPlayersDto){
        teamService.saveTeamWithPlayersDto(teamWithPlayersDto);
        return ResponseEntity.ok("Team saved successfully");
    }

    @GetMapping("/team/seasons/{id}")
    public ResponseEntity<List<String>> getPossibleSeasonFromTeam(@PathVariable("id") Long id){
        return ResponseEntity.ok(teamService.getPossibleSeasonFromTeam(id));
    }
}

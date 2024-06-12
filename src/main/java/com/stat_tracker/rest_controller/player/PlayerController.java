package com.stat_tracker.rest_controller.player;

import com.stat_tracker.dto.player.PlayerDto;
import com.stat_tracker.dto.player.PlayerWithStatsTotalsWithSeasonDto;
import com.stat_tracker.dto.player.PlayerWithTeamDto;
import com.stat_tracker.dto.team.helper.Record;
import com.stat_tracker.service.player.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class PlayerController {
    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/player/all")
    public ResponseEntity<List<PlayerWithTeamDto>> findAllPlayersWithTeamDto(){
        return ResponseEntity.ok(playerService.findALlPlayersWithTeamDto());
    }
    @GetMapping("/player/{id}")
    public ResponseEntity<PlayerWithTeamDto> findPlayerWithTeamDto(@PathVariable("id") Long id){
        return ResponseEntity.ok(playerService.findPlayerWithTeamDto(id));
    }
    @GetMapping("/player/records/{id}/{season}")
    public ResponseEntity<List<Record>> findPlayerRecordsDto(@PathVariable("id")Long id, @PathVariable("season")String season){
        return ResponseEntity.ok(playerService.findPlayerRecords(id, season));
    }
    @GetMapping("/player/totals/{id}")
    public ResponseEntity<List<PlayerWithStatsTotalsWithSeasonDto>> findPlayerWithStatsTotalsWithSeason(@PathVariable("id")Long id){
        return ResponseEntity.ok(playerService.findPlayerWithStatsTotalsWithSeason(id));
    }
    @PostMapping("/player/remove_team")
    public ResponseEntity<String> removePlayerFromTeam(@RequestBody PlayerDto playerDto){
        playerService.removePlayerFromTeam(playerDto);
        return ResponseEntity.ok("Player removed from team successfully");
    }
    @GetMapping("/player/seasons/{id}")
    public ResponseEntity<List<String>> getPossibleSeasonFromPlayer(@PathVariable("id") Long id){
        return ResponseEntity.ok(playerService.getPossibleSeasonFromTeam(id));
    }

    @PostMapping("/player/save")
    public ResponseEntity<String> saveTeamWithPlayersDto(@RequestBody PlayerWithTeamDto playerWithTeamDto){
        playerService.savePlayerWithTeamDto(playerWithTeamDto);
        System.out.println(playerWithTeamDto);
        return ResponseEntity.ok("Team saved successfully");
    }
}

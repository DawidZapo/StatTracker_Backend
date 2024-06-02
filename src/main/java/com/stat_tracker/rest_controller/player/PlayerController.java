package com.stat_tracker.rest_controller.player;

import com.stat_tracker.dto.player.PlayerDto;
import com.stat_tracker.entity.player.Player;
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
    @GetMapping("/players")
    public ResponseEntity<List<Player>> getPlayers(){
        return ResponseEntity.ok(playerService.findALl());
    }
    @PostMapping("/player/save")
    public ResponseEntity<Player> savePlayer(@RequestBody Player player){
        return ResponseEntity.ok(playerService.savePlayer(player));
    }

    @PostMapping("/player/remove_team")
    public ResponseEntity<String> removePlayerFromTeam(@RequestBody PlayerDto playerDto){
        playerService.removePlayerFromTeam(playerDto);
        return ResponseEntity.ok("Player removed from team successfully");
    }
}

package com.stat_tracker.rest_controller.player;

import com.stat_tracker.entity.player.Player;
import com.stat_tracker.service.player.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlayerController {
    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }
    @GetMapping("/players")
    public List<Player> getPlayers(){
        return playerService.findALl();
    }
    @PostMapping("/player/save")
    public ResponseEntity<Player> savePlayer(@RequestBody Player player){
        return ResponseEntity.ok(playerService.savePlayer(player));
    }
}

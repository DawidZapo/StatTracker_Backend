package com.stat_tracker.rest_controller.player;

import com.stat_tracker.model.entity.player.Player;
import com.stat_tracker.service.player.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerRestController {
    private PlayerService playerService;

    public PlayerRestController(PlayerService playerService) {
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

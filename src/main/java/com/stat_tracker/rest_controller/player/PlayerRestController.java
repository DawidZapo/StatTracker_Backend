package com.stat_tracker.rest_controller.player;

import com.stat_tracker.entity.player.Player;
import com.stat_tracker.service.player.PlayerService;
import org.springframework.web.bind.annotation.GetMapping;
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
}

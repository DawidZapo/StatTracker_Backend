package com.stat_tracker.rest_controller.player;

import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.service.player.StatPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StatPlayerController {
    private StatPlayerService statPlayerService;

    @Autowired
    public StatPlayerController(StatPlayerService statPlayerService) {
        this.statPlayerService = statPlayerService;
    }
    @GetMapping("/stat_players")
    public ResponseEntity<List<StatPlayer>> getStatPlayers(){
        return ResponseEntity.ok(statPlayerService.findAll());
    }
}

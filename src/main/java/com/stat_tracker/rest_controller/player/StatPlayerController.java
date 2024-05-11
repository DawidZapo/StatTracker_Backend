package com.stat_tracker.rest_controller.player;

import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.repository.player.StatPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StatPlayerController {
    private StatPlayerRepository statPlayerRepository;

    @Autowired
    public StatPlayerController(StatPlayerRepository statPlayerRepository) {
        this.statPlayerRepository = statPlayerRepository;
    }
    @GetMapping("/stat_players")
    public List<StatPlayer> getStatPlayers(){
        return statPlayerRepository.findAll();
    }
}

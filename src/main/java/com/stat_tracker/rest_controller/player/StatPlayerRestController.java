package com.stat_tracker.rest_controller.player;

import com.stat_tracker.model.entity.player.StatPlayer;
import com.stat_tracker.repository.player.StatPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatPlayerRestController {
    private StatPlayerRepository statPlayerRepository;

    @Autowired
    public StatPlayerRestController(StatPlayerRepository statPlayerRepository) {
        this.statPlayerRepository = statPlayerRepository;
    }
    @GetMapping("/stat_players")
    public List<StatPlayer> getStatPlayers(){
        return statPlayerRepository.findAll();
    }
}

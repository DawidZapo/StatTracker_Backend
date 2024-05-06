package com.stat_tracker.rest_controller.game;

import com.stat_tracker.entity.game.Game;
import com.stat_tracker.repository.game.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameRestController {
    private GameRepository gameRepository;

    @Autowired
    public GameRestController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
    @GetMapping("/games")
    public List<Game> getGames(){
        return gameRepository.findAll();
    }
}

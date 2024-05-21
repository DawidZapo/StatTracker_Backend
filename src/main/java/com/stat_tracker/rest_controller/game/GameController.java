package com.stat_tracker.rest_controller.game;

import com.stat_tracker.dto.game.GameWithPlaysDto;
import com.stat_tracker.dto.game.GameWithStatTeamsDto;
import com.stat_tracker.entity.game.Game;
import com.stat_tracker.service.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameController {
    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }
    @GetMapping("/games")
    public ResponseEntity<List<Game>> getGames(){
        return ResponseEntity.ok(gameService.getAllGames());
    }
    @GetMapping("/game_with_plays/{id}")
    public ResponseEntity<GameWithPlaysDto> getGameWithPlays(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.getGameWithPlays(id));
    }

    @GetMapping("/game_with_stat_teams/{id}")
    public ResponseEntity<GameWithStatTeamsDto> getGameWithStatTeams(@PathVariable Long id){
        return ResponseEntity.ok(gameService.getGameWithStatsTeam(id));
    }

}

package com.stat_tracker.rest_controller.game;

import com.stat_tracker.dto.game.GameWithPlaysDto;
import com.stat_tracker.dto.game.GameWithStatTeamsDto;
import com.stat_tracker.dto.game.GameWithTeamNamesDto;
import com.stat_tracker.service.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class GameController {
    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }
    @GetMapping("/game/all")
    public ResponseEntity<List<GameWithTeamNamesDto>> getGames(){
        return ResponseEntity.ok(gameService.getAllGamesWithTeamNamesDto());
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

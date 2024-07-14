package com.stat_tracker.rest_controller.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stat_tracker.dto.game.*;
import com.stat_tracker.dto.plays.*;
import com.stat_tracker.service.game.GameService;
import com.stat_tracker.service.game.GameUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class GameController {
    private GameService gameService;
    private GameUpdateService gameUpdateService;
    private ObjectMapper objectMapper;

    @Autowired
    public GameController(GameService gameService, GameUpdateService gameUpdateService, ObjectMapper objectMapper) {
        this.gameService = gameService;
        this.gameUpdateService = gameUpdateService;
        this.objectMapper = objectMapper;
    }
    @GetMapping("/game/all")
    public ResponseEntity<List<GameWithTeamNamesDto>> getGames(){
        return ResponseEntity.ok(gameService.getAllGamesWithTeamNamesDto());
    }
    @GetMapping("/game_with_plays/{id}")
    public ResponseEntity<GameWithPlaysDto> getGameWithPlays(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.getGameWithPlays(id));
    }
    @GetMapping("/game/stat_teams/{id}")
    public ResponseEntity<GameWithStatTeamsDto> getGameWithStatTeams(@PathVariable Long id){
        return ResponseEntity.ok(gameService.getGameWithStatsTeam(id));
    }

    @PostMapping("/game/create")
    public ResponseEntity<Long> createGame(@RequestBody GameCreatedDto gameCreatedDto){
        return ResponseEntity.ok(gameService.createGame(gameCreatedDto));
    }

    @GetMapping("/game/to_handle/{id}")
    public ResponseEntity<GameToHandleDto> getGameToHandle(@PathVariable("id")Long id){
        return ResponseEntity.ok(gameService.findGameToHandle(id));
    }

    @PostMapping("/game/save")
    public ResponseEntity<GameToHandleDto> saveGame(@RequestBody GameToHandleDto gameToHandle){
       // to be finished

        for (int i = 0; i < gameToHandle.getPlays().size(); i++) {
            PlayDto playDto = gameToHandle.getPlays().get(i);
            switch (playDto.getPlayType()) {
                case "ASSIST":
                    AssistDto assistDto = objectMapper.convertValue(playDto, AssistDto.class);
                    gameToHandle.getPlays().set(i, assistDto); // Zaktualizuj element w kolekcji plays na AssistDto
                    break;
                case "BLOCK":
                    BlockDto blockDto = objectMapper.convertValue(playDto, BlockDto.class);
                    gameToHandle.getPlays().set(i, blockDto); // Zaktualizuj element w kolekcji plays na BlockDto
                    break;
                case "FOUL":
                    FoulDto foulDto = objectMapper.convertValue(playDto, FoulDto.class);
                    gameToHandle.getPlays().set(i, foulDto); // Zaktualizuj element w kolekcji plays na FoulDto
                    break;
                case "REBOUND":
                    ReboundDto reboundDto = objectMapper.convertValue(playDto, ReboundDto.class);
                    gameToHandle.getPlays().set(i, reboundDto); // Zaktualizuj element w kolekcji plays na ReboundDto
                    break;
                case "SHOTPLAY":
                    ShotPlayDto shotPlayDto = objectMapper.convertValue(playDto, ShotPlayDto.class);
                    gameToHandle.getPlays().set(i, shotPlayDto); // Zaktualizuj element w kolekcji plays na ShotPlayDto
                    break;
                case "STEAL":
                    StealDto stealDto = objectMapper.convertValue(playDto, StealDto.class);
                    gameToHandle.getPlays().set(i, stealDto); // Zaktualizuj element w kolekcji plays na StealDto
                    break;
                case "TURNOVER":
                    TurnoverDto turnoverDto = objectMapper.convertValue(playDto, TurnoverDto.class);
                    gameToHandle.getPlays().set(i, turnoverDto); // Zaktualizuj element w kolekcji plays na TurnoverDto
                    break;
                default:
                    throw new RuntimeException("Play not recognized as any DTO class");
            }
        }

        return ResponseEntity.ok(gameUpdateService.saveGameToHandle(gameToHandle));
    }

}

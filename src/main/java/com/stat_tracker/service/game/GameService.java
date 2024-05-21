package com.stat_tracker.service.game;

import com.stat_tracker.dto.game.GameWithPlaysDto;
import com.stat_tracker.dto.game.GameWithStatTeamsDto;
import com.stat_tracker.entity.game.Game;
import com.stat_tracker.repository.game.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
    public GameWithPlaysDto getGameWithPlays(Long id){
        Optional<Game> game = gameRepository.findById(id);
        if(!game.isPresent()){
            throw new RuntimeException("Game not found - id: " + id);
        }
        return new GameWithPlaysDto(game.get().getId(), game.get().getPlays());
    }
    public GameWithStatTeamsDto getGameWithStatsTeam(Long id){
        Optional<Game> game = gameRepository.findById(id);
        if(!game.isPresent()){
            throw new RuntimeException("Game not found - id: " + id);
        }
        return new GameWithStatTeamsDto(game.get().getId(), game.get().getHome(),game.get().getAway());
    }
    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }
}

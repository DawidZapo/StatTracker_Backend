package com.stat_tracker.service.game;

import com.stat_tracker.dto.game.GameToHandleDto;
import com.stat_tracker.entity.game.Game;
import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.repository.game.GameRepository;
import com.stat_tracker.repository.play.PlayRepository;
import com.stat_tracker.repository.player.StatPlayerRepository;
import com.stat_tracker.utils.GameUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class GameUpdateService {
    private GameRepository gameRepository;
    private StatPlayerRepository statPlayerRepository;
    private PlayRepository playRepository;

    @Autowired
    public GameUpdateService(GameRepository gameRepository, StatPlayerRepository statPlayerRepository, PlayRepository playRepository) {
        this.gameRepository = gameRepository;
        this.statPlayerRepository = statPlayerRepository;
        this.playRepository = playRepository;
    }

    public GameToHandleDto saveGameToHandle(GameToHandleDto gameToHandleDto) {
        // to be finished

        Game game = gameRepository.findById(gameToHandleDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Game not found"));

        Set<StatPlayer> minorPlayers = findMinorPlayer(game);

        GameUtils.updateGame(game, gameToHandleDto, minorPlayers);
        return null;
    }

    private HashSet<StatPlayer> findMinorPlayer(Game game){
        HashSet<StatPlayer> minorPlayers = new HashSet<>();

        game.getPlays().forEach(play -> {
            if (play.getMinorPlayer() != null) {
                StatPlayer minorPlayer = statPlayerRepository.findById(play.getMinorPlayer().getId())
                        .orElseThrow(() -> new EntityNotFoundException("Minor StatPlayer not found"));
                minorPlayers.add(minorPlayer);
            }
        });

        return minorPlayers;
    }
}

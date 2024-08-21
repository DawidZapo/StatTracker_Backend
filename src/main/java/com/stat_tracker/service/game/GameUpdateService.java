package com.stat_tracker.service.game;

import com.stat_tracker.dto.game.GameToHandleDto;
import com.stat_tracker.entity.game.Game;
import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.repository.game.GameRepository;
import com.stat_tracker.repository.play.PlayRepository;
import com.stat_tracker.repository.player.StatPlayerRepository;
import com.stat_tracker.service.stat.StatLineService;
import com.stat_tracker.utils.GameUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class GameUpdateService {
    private GameRepository gameRepository;
    private StatPlayerRepository statPlayerRepository;
    private PlayRepository playRepository;
    private StatLineService statLineService;

    @Autowired
    public GameUpdateService(GameRepository gameRepository, StatPlayerRepository statPlayerRepository, PlayRepository playRepository, StatLineService statLineService) {
        this.gameRepository = gameRepository;
        this.statPlayerRepository = statPlayerRepository;
        this.playRepository = playRepository;
        this.statLineService = statLineService;
    }

    @Transactional
    public GameToHandleDto saveGameToHandle(GameToHandleDto gameToHandleDto) {
        GameUtils.convertPlaysToPlayDto(gameToHandleDto.getPlays());
        gameToHandleDto.getHome().getPlayers().forEach(player -> GameUtils.convertPlaysToPlayDto(player.getPlays()));
        gameToHandleDto.getAway().getPlayers().forEach(player -> GameUtils.convertPlaysToPlayDto(player.getPlays()));

        Game game = gameRepository.findById(gameToHandleDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Game not found"));

        Set<StatPlayer> minorPlayers = findMinorPlayer(game);

        GameUtils.updateGame(game, gameToHandleDto, minorPlayers);
        statLineService.save(game.getHome().getStatLine());
        statLineService.save(game.getAway().getStatLine());
        game.getHome().getStatPlayers().forEach(player -> statLineService.save(player.getStatLine()));
        game.getAway().getStatPlayers().forEach(player -> statLineService.save(player.getStatLine()));


        return GameUtils.createGameToHandle(gameRepository.save(game));
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

package com.stat_tracker.service.play;

import com.stat_tracker.dto.plays.ShotPlayDto;
import com.stat_tracker.entity.game.Game;
import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.entity.plays.ShotPlay;
import com.stat_tracker.entity.plays.abstract_play.Play;
import com.stat_tracker.repository.play.PlayRepository;
import com.stat_tracker.service.game.GameService;
import com.stat_tracker.service.player.StatPlayerService;
import com.stat_tracker.utils.PlayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayService {
    private PlayRepository playRepository;
    private GameService gameService;
    private StatPlayerService statPlayerService;

    @Autowired
    public PlayService(PlayRepository playRepository, GameService gameService, StatPlayerService statPlayerService) {
        this.playRepository = playRepository;
        this.gameService = gameService;
        this.statPlayerService = statPlayerService;
    }

    public List<Play> findAll(){
        return playRepository.findAll();
    }

    public Long savePlay(ShotPlayDto shotPlayDto){
        System.out.println(shotPlayDto);
        StatPlayer statPlayer = statPlayerService.findById(shotPlayDto.getStatPlayerId());
        Game game = gameService.findById(shotPlayDto.getGameId());

        ShotPlay shotPlay = PlayUtils.createShotPlay(shotPlayDto, game, statPlayer);

        shotPlay = playRepository.save(shotPlay);
        System.out.println(shotPlay);
        return shotPlay.getId();
    }
}

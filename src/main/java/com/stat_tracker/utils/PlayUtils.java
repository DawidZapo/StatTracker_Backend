package com.stat_tracker.utils;

import com.stat_tracker.dto.plays.ShotPlayDto;
import com.stat_tracker.entity.game.Game;
import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.entity.plays.ShotPlay;

public class PlayUtils {
    public static ShotPlay createShotPlay(ShotPlayDto shotPlayDto, Game game, StatPlayer statPlayer){
        ShotPlay shotPlay = new ShotPlay();
        shotPlay.setId(null);

        shotPlay.setGame(game);
        game.addPlay(shotPlay);

        shotPlay.setStatPlayer(statPlayer);
        statPlayer.addPlay(shotPlay);

        shotPlay.setDuration(shotPlayDto.getDuration());
        shotPlay.setComments(shotPlayDto.getComments());
        shotPlay.setHand(shotPlayDto.getHand());
        shotPlay.setType(shotPlayDto.getType());
        shotPlay.setOffTheDribble(shotPlayDto.getOffTheDribble());
        shotPlay.setZone(shotPlayDto.getZone());
        shotPlay.setMade(shotPlayDto.getMade());
        shotPlay.setContested(shotPlayDto.getContested());
        shotPlay.setWorth(shotPlayDto.getWorth());

        return shotPlay;
    }
}

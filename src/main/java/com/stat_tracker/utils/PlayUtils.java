package com.stat_tracker.utils;

import com.stat_tracker.dto.plays.*;
import com.stat_tracker.entity.game.Game;
import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.entity.plays.*;
import com.stat_tracker.entity.plays.abstract_play.Play;

public class PlayUtils {
    public static ShotPlay createShotPlay(ShotPlayDto shotPlayDto, Game game, StatPlayer statPlayer){
        ShotPlay shotPlay = new ShotPlay();
        shotPlay.setId(null);

        setCommonFields(shotPlay, shotPlayDto, game, statPlayer);

        shotPlay.setType(shotPlayDto.getType());
        shotPlay.setOffTheDribble(shotPlayDto.getOffTheDribble());
        shotPlay.setZone(shotPlayDto.getZone());
        shotPlay.setMade(shotPlayDto.getMade());
        shotPlay.setContested(shotPlayDto.getContested());
        shotPlay.setWorth(shotPlayDto.getWorth());

        return shotPlay;
    }

    public static Assist createAssist(AssistDto assistDto, Game game, StatPlayer statPlayer, StatPlayer toStatPlayer){
        Assist assist = new Assist();
        assist.setId(null);

        setCommonFields(assist, assistDto, game, statPlayer);

        assist.setType(assistDto.getType());
        assist.setToStatPlayer(toStatPlayer);

        return assist;
    }

    public static Block createBlock(BlockDto blockDto, Game game, StatPlayer statPlayer, StatPlayer blockedStatPlayer){
        Block block = new Block();
        block.setId(null);

        setCommonFields(block, blockDto, game, statPlayer);

        block.setBlockedStatPlayer(blockedStatPlayer);
        block.setInThePaint(blockDto.getInThePaint());

        return block;
    }

    public static Foul createFoul(FoulDto foulDto, Game game, StatPlayer statPlayer, StatPlayer foulOnStatPlayer){
        Foul foul = new Foul();
        foul.setId(null);

        setCommonFields(foul, foulDto, game, statPlayer);

        foul.setFoulOnStatPlayer(foulOnStatPlayer);
        foul.setType(foulDto.getType());

        return foul;
    }

    public static Rebound createRebound(ReboundDto reboundDto, Game game, StatPlayer statPlayer){
        Rebound rebound = new Rebound();
        rebound.setId(null);

        setCommonFields(rebound, reboundDto, game, statPlayer);

        rebound.setOffensive(reboundDto.isOffensive());

        return rebound;
    }

    public static Steal createSteal(StealDto stealDto, Game game, StatPlayer statPlayer, StatPlayer turnoverForStatPlayer){
        Steal steal = new Steal();
        steal.setId(null);

        setCommonFields(steal, stealDto, game, statPlayer);

        steal.setTurnoverForStatPlayer(turnoverForStatPlayer);

        return steal;
    }

    public static Turnover createTurnover(TurnoverDto turnoverDto, Game game, StatPlayer statPlayer, StatPlayer stealForStatPlayer){
        Turnover turnover = new Turnover();
        turnover.setId(null);

        setCommonFields(turnover, turnoverDto, game, statPlayer);

        turnover.setStealForStatPlayer(stealForStatPlayer);
        turnover.setType(turnoverDto.getType());

        return turnover;
    }

    private static void setCommonFields(Play play, PlayDto playDto, Game game, StatPlayer statPlayer) {
        play.setGame(game);
        game.addPlay(play);

        play.setStatPlayer(statPlayer);
        statPlayer.addPlay(play);

        play.setDuration(playDto.getDuration());
        play.setComments(playDto.getComments());
        play.setHand(playDto.getHand());
    }
}

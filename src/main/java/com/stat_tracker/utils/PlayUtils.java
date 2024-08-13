package com.stat_tracker.utils;

import com.stat_tracker.dto.game.GameToHandleDto;
import com.stat_tracker.dto.plays.*;
import com.stat_tracker.entity.game.Game;
import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.entity.plays.*;
import com.stat_tracker.entity.plays.abstract_play.Play;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class PlayUtils {
    public static ShotPlay createShotPlay(ShotPlayDto shotPlayDto, Integer order, Game game, StatPlayer statPlayer){
        ShotPlay shotPlay = new ShotPlay();
        shotPlay.setId(null);

        setCommonFields(shotPlay, shotPlayDto, game, statPlayer, order);

        shotPlay.setType(shotPlayDto.getType());
        shotPlay.setOffTheDribble(shotPlayDto.getOffTheDribble());
        shotPlay.setZone(shotPlayDto.getZone());
        shotPlay.setMade(shotPlayDto.getMade());
        shotPlay.setContested(shotPlayDto.getContested());
        shotPlay.setWorth(shotPlayDto.getWorth());

        return shotPlay;
    }

    public static Assist createAssist(AssistDto assistDto, Integer order, Game game, StatPlayer statPlayer, StatPlayer toStatPlayer){
        Assist assist = new Assist();
        assist.setId(null);

        setCommonFields(assist, assistDto, game, statPlayer, order);

        assist.setType(assistDto.getType());
        assist.setToStatPlayer(toStatPlayer);

        return assist;
    }

    public static Block createBlock(BlockDto blockDto, Integer order, Game game, StatPlayer statPlayer, StatPlayer blockedStatPlayer){
        Block block = new Block();
        block.setId(null);

        setCommonFields(block, blockDto, game, statPlayer, order);

        block.setBlockedStatPlayer(blockedStatPlayer);
        block.setInThePaint(blockDto.getInThePaint());

        return block;
    }

    public static Foul createFoul(FoulDto foulDto, Integer order, Game game, StatPlayer statPlayer, StatPlayer foulOnStatPlayer){
        Foul foul = new Foul();
        foul.setId(null);

        setCommonFields(foul, foulDto, game, statPlayer, order);

        foul.setFoulOnStatPlayer(foulOnStatPlayer);
        foul.setType(foulDto.getType());

        return foul;
    }

    public static Rebound createRebound(ReboundDto reboundDto, Integer order, Game game, StatPlayer statPlayer){
        Rebound rebound = new Rebound();
        rebound.setId(null);

        setCommonFields(rebound, reboundDto, game, statPlayer, order);

        rebound.setOffensive(reboundDto.isOffensive());

        return rebound;
    }

    public static Steal createSteal(StealDto stealDto, Integer order, Game game, StatPlayer statPlayer, StatPlayer turnoverForStatPlayer){
        Steal steal = new Steal();
        steal.setId(null);

        setCommonFields(steal, stealDto, game, statPlayer, order);

        steal.setTurnoverForStatPlayer(turnoverForStatPlayer);

        return steal;
    }

    public static Turnover createTurnover(TurnoverDto turnoverDto, Integer order, Game game, StatPlayer statPlayer, StatPlayer stealForStatPlayer){
        Turnover turnover = new Turnover();
        turnover.setId(null);

        setCommonFields(turnover, turnoverDto, game, statPlayer, order);

        turnover.setStealForStatPlayer(stealForStatPlayer);
        turnover.setType(turnoverDto.getType());

        return turnover;
    }

    public static Violation createViolation(ViolationDto violationDto, Integer order, Game game, StatPlayer statPlayer){
        Violation violation = new Violation();
        violation.setId(null);

        setCommonFields(violation, violationDto, game, statPlayer, order);

        violation.setType(violationDto.getType());

        return violation;
    }

    private static void setCommonFields(Play play, PlayDto playDto, Game game, StatPlayer statPlayer, Integer order) {
        play.setGame(game);
        game.addPlay(play);

        play.setStatPlayer(statPlayer);
        statPlayer.addPlay(play);

        play.setTimeRemaining(playDto.getTimeRemaining());
        play.setQuarter(playDto.getQuarter());
        play.setComments(playDto.getComments());
        play.setHand(playDto.getHand());
        play.setOrder(order);
    }

    private static void updateCommonPlayPart(Play play, PlayDto playDto){
        play.setQuarter(playDto.getQuarter());
        play.setHand(playDto.getHand());
        play.setComments(playDto.getComments());
        play.setTimeRemaining(playDto.getTimeRemaining());
        play.setOrder(playDto.getOrder());
    }

    public static void updateAssist(Assist assist, AssistDto assistDto, StatPlayer toStatPlayer){
        updateCommonPlayPart(assist, assistDto);
        assist.setType(assistDto.getType());
        assist.setToStatPlayer(toStatPlayer);
    }

    public static void updateBlock(Block block, BlockDto blockDto, StatPlayer blockedStatPlayer){
        updateCommonPlayPart(block, blockDto);
        block.setInThePaint(blockDto.getInThePaint());
        block.setBlockedStatPlayer(blockedStatPlayer);
    }

    public static void updateFoul(Foul foul, FoulDto foulDto, StatPlayer foulOnStatPlayer){
        updateCommonPlayPart(foul, foulDto);
        foul.setType(foulDto.getType());
        foul.setFoulOnStatPlayer(foulOnStatPlayer);
    }

    public static void updateRebound(Rebound rebound, ReboundDto reboundDto){
        updateCommonPlayPart(rebound, reboundDto);
        rebound.setOffensive(reboundDto.isOffensive());
    }

    public static void updateViolation(Violation violation, ViolationDto violationDto){
        updateCommonPlayPart(violation, violationDto);
        violation.setType(violationDto.getType());
    }

    public static void updateShotPlay(ShotPlay shotPlay, ShotPlayDto shotPlayDto){
        updateCommonPlayPart(shotPlay, shotPlayDto);
        shotPlay.setType(shotPlayDto.getType());
        shotPlay.setOffTheDribble(shotPlayDto.getOffTheDribble());
        shotPlay.setZone(shotPlayDto.getZone());
        shotPlay.setMade(shotPlayDto.getMade());
        shotPlay.setContested(shotPlayDto.getContested());
        shotPlay.setWorth(shotPlayDto.getWorth());
    }

    public static void updateSteal(Steal steal, StealDto stealDto, StatPlayer turnoverForStatPlayer){
        updateCommonPlayPart(steal, stealDto);
        steal.setTurnoverForStatPlayer(turnoverForStatPlayer);
    }

    public static void updateTurnover(Turnover turnover, TurnoverDto turnoverDto, StatPlayer stealForStatPlayer){
        updateCommonPlayPart(turnover, turnoverDto);
        turnover.setStealForStatPlayer(stealForStatPlayer);
        turnover.setType(turnoverDto.getType());
    }

    public static Optional<PlayDto> findMatchingPlayDto(Play play, List<PlayDto> playDtos) {
        return playDtos.stream().filter(playDto -> playDto.getId().equals(play.getId())).findFirst();
    }

    public static Optional<GameToHandleDto.PlayerDto> findMatchingStatPlayer(StatPlayer statPlayer, List<GameToHandleDto.PlayerDto> playersDto){
        return playersDto.stream().filter(playerDto -> playerDto.getStatPlayerId().equals(statPlayer.getId())).findFirst();
    }

    public static StatPlayer findMinorStatPlayer(Set<StatPlayer> statPlayerSet, Play play){
        if(play.getMinorPlayer() != null){
            return statPlayerSet.stream()
                    .filter(player -> player.getId().equals(play.getMinorPlayer().getId()))
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException("StatPlayer with id " + play.getMinorPlayer().getId() + " not found in minorStatPlayer set"));

        }
        else{
            return null;
        }

    }

}

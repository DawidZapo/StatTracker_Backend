package com.stat_tracker.utils;

import com.stat_tracker.dto.game.GameToHandleDto;
import com.stat_tracker.dto.plays.*;
import com.stat_tracker.entity.game.Game;
import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.entity.plays.*;
import com.stat_tracker.entity.plays.abstract_play.Play;
import com.stat_tracker.entity.plays.enums.Contested;
import com.stat_tracker.entity.stat.StatLine;
import com.stat_tracker.entity.team.StatTeam;
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


    public static void updateStatLine(Play play, StatTeam statTeam, StatPlayer statPlayer, StatTeam minorStatTeam, StatPlayer minorStatPlayer){
        switch (play.getPlayType()){
            case "ASSIST" -> updateAssistStatLine(statTeam.getStatLine(), statPlayer.getStatLine());
            case "BLOCK" -> updateBlockStatLine(statTeam.getStatLine(), statPlayer.getStatLine());
            case "FOUL" -> {
                if(minorStatPlayer != null & minorStatTeam != null){
                    updateFoulStatLine(statTeam.getStatLine(), statPlayer.getStatLine(), minorStatTeam.getStatLine(), minorStatPlayer.getStatLine());
                }
                else{
                    throw new RuntimeException("MinorStatPlayer and minorStatTeam are null for updateFoulStatLine (no foul on player)");
                }
            }
            case "REBOUND" -> updateReboundStatLine((Rebound) play, statTeam.getStatLine(), statPlayer.getStatLine());
            case "SHOTPLAY" -> updateShotPlayStatLine((ShotPlay) play, statTeam.getStatLine(), statPlayer.getStatLine());
            case "STEAL" -> updateStealStatLine(statPlayer.getStatLine(), statPlayer.getStatLine());
            case "TURNOVER" -> updateTurnoverStatLine(statTeam.getStatLine(), statPlayer.getStatLine());
            default -> throw new RuntimeException("Unknown play.getPlayType");
        }
    }

    private static void updateShotPlayStatLine(ShotPlay shotPlay, StatLine teamStatLine, StatLine playerStatLine){
        if(shotPlay.getWorth() == 1){
            teamStatLine.setFreeThrowAttempted(teamStatLine.getFreeThrowAttempted() + 1);
            playerStatLine.setFreeThrowAttempted(playerStatLine.getFreeThrowAttempted() + 1);
            if(shotPlay.getMade()){
                teamStatLine.setFreeThrowMade(teamStatLine.getFreeThrowMade() + 1);
                playerStatLine.setFreeThrowMade(playerStatLine.getFreeThrowMade() + 1);

                teamStatLine.setTotalPoints(teamStatLine.getTotalPoints() + 1);
                playerStatLine.setTotalPoints(playerStatLine.getTotalPoints() + 1);
            }
        }
        else if(shotPlay.getWorth() == 2){
            teamStatLine.setTwoAttempted(teamStatLine.getTwoAttempted() + 1);
            playerStatLine.setTwoAttempted(playerStatLine.getTwoAttempted() + 1);
            if(shotPlay.getMade()){
                teamStatLine.setTwoMade(teamStatLine.getTwoMade() + 1);
                playerStatLine.setTwoMade(playerStatLine.getTwoMade() + 1);

                teamStatLine.setTotalPoints(teamStatLine.getTotalPoints() + 2);
                playerStatLine.setTotalPoints(playerStatLine.getTotalPoints() + 2);
            }
            else{
                if(shotPlay.getContested().equals(Contested.BLOCKED)){
                    teamStatLine.setBlocksReceived(teamStatLine.getBlocksReceived() + 1);
                    playerStatLine.setBlocksReceived(playerStatLine.getBlocksReceived() + 1);
                }
            }
        }
        else if(shotPlay.getWorth() == 3){
            teamStatLine.setThreeAttempted(teamStatLine.getThreeAttempted() + 1);
            playerStatLine.setThreeAttempted(playerStatLine.getThreeAttempted() + 1);
            if(shotPlay.getMade()){
                teamStatLine.setThreeMade(teamStatLine.getThreeMade() + 1);
                playerStatLine.setThreeMade(playerStatLine.getThreeMade() + 1);

                teamStatLine.setTotalPoints(teamStatLine.getTotalPoints() + 3);
                playerStatLine.setTotalPoints(playerStatLine.getTotalPoints() + 3);
            }
            else{
                if(shotPlay.getContested().equals(Contested.BLOCKED)){
                    teamStatLine.setBlocksReceived(teamStatLine.getBlocksReceived() + 1);
                    playerStatLine.setBlocksReceived(playerStatLine.getBlocksReceived() + 1);
                }
            }
        }
        else{
            throw new RuntimeException("Shotplay worth unknown");
        }

        updateEval(teamStatLine, playerStatLine);
    }

    private static void updateAssistStatLine(StatLine teamStatLine, StatLine playerStatLine){
        teamStatLine.setAssists(teamStatLine.getAssists() + 1);
        playerStatLine.setAssists(playerStatLine.getAssists() + 1);
        updateEval(teamStatLine, playerStatLine);
    }

    private static void updateBlockStatLine(StatLine teamStatLine, StatLine playerStatLine){
        teamStatLine.setBlocks(teamStatLine.getBlocks() + 1);
        playerStatLine.setBlocks(playerStatLine.getBlocks() + 1);
        updateEval(teamStatLine, playerStatLine);
    }

    private static void updateFoulStatLine(StatLine teamStatLine, StatLine playerStatLine, StatLine opponentTeamStatLine, StatLine opponentPlayerStatLine){
        teamStatLine.setFouls(teamStatLine.getFouls() + 1);
        playerStatLine.setFouls(playerStatLine.getFouls() + 1);

        opponentTeamStatLine.setForcedFouls(opponentTeamStatLine.getForcedFouls() + 1);
        opponentPlayerStatLine.setForcedFouls(opponentPlayerStatLine.getForcedFouls() + 1);

        updateEval(teamStatLine, playerStatLine);
    }

    private static void updateReboundStatLine(Rebound rebound, StatLine teamStatLine, StatLine playerStatLine){
        if(rebound.getOffensive()){
            teamStatLine.setOffRebounds(teamStatLine.getOffRebounds() + 1);
            playerStatLine.setOffRebounds(playerStatLine.getOffRebounds() + 1);
        }
        else{
            teamStatLine.setDefRebounds(teamStatLine.getDefRebounds() + 1);
            playerStatLine.setDefRebounds(playerStatLine.getDefRebounds() + 1);
        }
        updateEval(teamStatLine, playerStatLine);
    }

    private static void updateStealStatLine(StatLine teamStatLine, StatLine playerStatLine){
        teamStatLine.setSteals(teamStatLine.getSteals() + 1);
        playerStatLine.setSteals(playerStatLine.getSteals() + 1);
        updateEval(teamStatLine, playerStatLine);
    }

    private static void updateTurnoverStatLine(StatLine teamStatLine, StatLine playerStatLine){
        teamStatLine.setTurnovers(teamStatLine.getTurnovers() + 1);
        playerStatLine.setTurnovers(playerStatLine.getTurnovers() + 1);
        updateEval(teamStatLine, playerStatLine);
    }

    private static void updateEval(StatLine teamStatLine, StatLine playerStatline){
        teamStatLine.setEvaluation(StatsUtils.calculateNewEvaluation(teamStatLine));
        playerStatline.setEvaluation(StatsUtils.calculateNewEvaluation(playerStatline));
    }

}

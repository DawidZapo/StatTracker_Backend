package com.stat_tracker.utils;

import com.stat_tracker.dto.game.GameCreatedDto;
import com.stat_tracker.dto.game.GameToHandleDto;
import com.stat_tracker.dto.game.GameWithStatTeamsDto;
import com.stat_tracker.dto.game.GameWithTeamNamesDto;
import com.stat_tracker.dto.plays.*;
import com.stat_tracker.dto.stat.StatLineDto;
import com.stat_tracker.entity.game.Game;
import com.stat_tracker.entity.player.Player;
import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.entity.plays.*;
import com.stat_tracker.entity.plays.abstract_play.Play;
import com.stat_tracker.entity.score.Score;
import com.stat_tracker.entity.stat.StatLine;
import com.stat_tracker.entity.team.StatTeam;
import com.stat_tracker.entity.team.Team;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class GameUtils {
    public static GameWithTeamNamesDto createGameWithTeamNamesDto(Game game){
        String[] timeAndDate = getDateAndTimeFromLocalDateTime(game.getLocalDateTime());

        GameWithTeamNamesDto gameWithTeamNamesDto = new GameWithTeamNamesDto();
        gameWithTeamNamesDto.setId(game.getId());
        gameWithTeamNamesDto.setHome(game.getHome().getTeam().getName());
        gameWithTeamNamesDto.setAway(game.getAway().getTeam().getName());
        gameWithTeamNamesDto.setDate(timeAndDate[0]);
        gameWithTeamNamesDto.setTime(timeAndDate[1]);
        gameWithTeamNamesDto.setOfficial(game.getOfficial());
        gameWithTeamNamesDto.setSeason(game.getSeason());

        return gameWithTeamNamesDto;
    }

    private static String[] getDateAndTimeFromLocalDateTime(LocalDateTime localDateTime){
        return localDateTime.toString().split("T");
    }

    public static GameWithStatTeamsDto createGameWithStatTeams(Game game) {
        String[] dateAndTime = getDateAndTimeFromLocalDateTime(game.getLocalDateTime());

        GameWithStatTeamsDto gameToReturn = new GameWithStatTeamsDto();
        gameToReturn.setId(game.getId());
        gameToReturn.setDate(dateAndTime[0]);
        gameToReturn.setTime(dateAndTime[1]);
        gameToReturn.setSeason(game.getSeason());
        gameToReturn.setOfficial(game.getOfficial());

        gameToReturn.setHome(StatTeamAndPlayerUtils.createStatTeamDto(game.getHome()));
        gameToReturn.setAway(StatTeamAndPlayerUtils.createStatTeamDto(game.getAway()));

        return gameToReturn;
    }

    public static Game createGame(GameCreatedDto gameCreatedDto, Team home, Team away, List<Player> homePlayers, List<Player> awayPlayers){
        Game game = new Game();
        game.setLocalDateTime(LocalDateTime.of(gameCreatedDto.getDate(), gameCreatedDto.getTime()));
        game.setSeason(gameCreatedDto.getSeason());
        game.setOfficial(gameCreatedDto.isOfficial());
        game.setQuarterLengthMin(gameCreatedDto.getQuarterLengthMin());
        game.setOvertimeLengthMin(gameCreatedDto.getOvertimeLengthMin());

        game.setCurrentQuarterTimeRemainingMs(gameCreatedDto.getQuarterLengthMin() * 60000L);
        game.setCurrentQuarter(1);

        StatTeam homeStatTeam = StatTeamAndPlayerUtils.createStatTeam(home,game,true, gameCreatedDto, homePlayers);
        StatTeam awayStatTeam = StatTeamAndPlayerUtils.createStatTeam(away,game, false, gameCreatedDto, awayPlayers);

        game.setHome(homeStatTeam);
        game.setAway(awayStatTeam);


        return game;
    }

    public static GameToHandleDto createGameToHandle(Game game){
        GameToHandleDto gameToHandleDto = new GameToHandleDto();
        gameToHandleDto.setId(game.getId());
        gameToHandleDto.setLocalDateTime(game.getLocalDateTime());
        gameToHandleDto.setSeason(game.getSeason());
        gameToHandleDto.setOfficial(game.getOfficial());
        gameToHandleDto.setQuarterLengthMin(game.getQuarterLengthMin());
        gameToHandleDto.setOvertimeLengthMin(game.getOvertimeLengthMin());
        gameToHandleDto.setCurrentQuarterTimeRemainingMs(game.getCurrentQuarterTimeRemainingMs());
        gameToHandleDto.setCurrentQuarter(game.getCurrentQuarter());

        gameToHandleDto.setHome(createTeamDto(game.getHome()));
        gameToHandleDto.setAway(createTeamDto(game.getAway()));

        gameToHandleDto.setPlays(createPlaysDto(game.getPlays()));


        return gameToHandleDto;
    }

    private static GameToHandleDto.TeamDto createTeamDto(StatTeam statTeam){
        GameToHandleDto.TeamDto team = new GameToHandleDto.TeamDto();
        team.setTeamId(statTeam.getTeam().getId());
        team.setStatTeamId(statTeam.getId());
        team.setName(statTeam.getTeam().getName());
        team.setPlayers(statTeam.getStatPlayers().stream().map(GameUtils::createPlayerDto).collect(Collectors.toList()));
        team.setStats(StatsUtils.createStatLineDto(statTeam.getStatLine()));
        team.setScores(statTeam.getScores().stream().map(GameUtils::createScoreDto).collect(Collectors.toList()));

        return team;
    }

    private static GameToHandleDto.PlayerDto createPlayerDto(StatPlayer statPlayer){
        GameToHandleDto.PlayerDto player = new GameToHandleDto.PlayerDto();
        player.setPlayerId(statPlayer.getPlayer().getId());
        player.setStatPlayerId(statPlayer.getId());
        player.setFirstName(statPlayer.getPlayer().getFirstName());
        player.setLastName(statPlayer.getPlayer().getLastName());
        player.setShirtNumber(statPlayer.getShirtNumber());
        player.setStartingFive(statPlayer.getStartingFive());
        player.setOnCourt(statPlayer.getOnCourt());
        player.setPositionOnCourt(statPlayer.getPositionOnCourt());
        player.setStats(StatsUtils.createStatLineDto(statPlayer.getStatLine()));
        player.setPlays(createPlaysDto(statPlayer.getPlays()));
        player.setDominantHand(statPlayer.getPlayer().getDominantHand());

        return player;
    }

    private static GameToHandleDto.ScoreDto createScoreDto(Score score){
        GameToHandleDto.ScoreDto scoreDto = new GameToHandleDto.ScoreDto();
        scoreDto.setId(score.getId());
        scoreDto.setWorth(score.getWorth());
        scoreDto.setPart(score.getPart());

        return scoreDto;
    }

    private static List<PlayDto> createPlaysDto(List<Play> plays){
        return plays.stream().sorted(
                        Comparator.comparingInt(Play::getQuarter)
                        .thenComparingLong(Play::getTimeRemaining).reversed()
                                .thenComparingLong(Play::getOrder))
                        .map(GameUtils::createPlayDto)
                        .collect(Collectors.toList());
    }

    private static PlayDto createPlayDto(Play play) {
        return switch (play) {
            case Assist assist -> new AssistDto(assist);
            case Block block -> new BlockDto(block);
            case Foul foul -> new FoulDto(foul);
            case Rebound rebound -> new ReboundDto(rebound);
            case ShotPlay shotPlay -> new ShotPlayDto(shotPlay);
            case Steal steal -> new StealDto(steal);
            case Turnover turnover -> new TurnoverDto(turnover);
            case Violation violation -> new ViolationDto(violation);
            default -> throw new RuntimeException("Play not found as an instance of some class");
        };
    }

    public static void updateGame(Game game, GameToHandleDto gameToHandleDto, Set<StatPlayer> statPlayerSet){
        game.setLocalDateTime(gameToHandleDto.getLocalDateTime());
        game.setOfficial(gameToHandleDto.getOfficial());
        game.setSeason(gameToHandleDto.getSeason());
        game.setCurrentQuarter(gameToHandleDto.getCurrentQuarter());
        game.setOvertimeLengthMin(gameToHandleDto.getOvertimeLengthMin());
        game.setQuarterLengthMin(gameToHandleDto.getQuarterLengthMin());
        game.setCurrentQuarterTimeRemainingMs(gameToHandleDto.getCurrentQuarterTimeRemainingMs());

        updateStatTeam(game.getHome(), gameToHandleDto.getHome(), statPlayerSet);
        updateStatTeam(game.getAway(), gameToHandleDto.getAway(), statPlayerSet);


        for (Play play : game.getPlays()) {
            Optional<PlayDto> matchingPlayDto = PlayUtils.findMatchingPlayDto(play, gameToHandleDto.getPlays());
            matchingPlayDto.ifPresent(playDto -> updatePlay(play, playDto, statPlayerSet));
        }

    }

    public static void updateStatTeam(StatTeam statTeam, GameToHandleDto.TeamDto teamDto, Set<StatPlayer> statPlayerSet){

        for(var statPlayer : statTeam.getStatPlayers()){
            Optional<GameToHandleDto.PlayerDto> matchingStatPlayer = PlayUtils.findMatchingStatPlayer(statPlayer,teamDto.getPlayers());
            matchingStatPlayer.ifPresent(playerDto -> updateStatPlayer(statPlayer, playerDto, statPlayerSet));
        }

        updateStatLine(statTeam.getStatLine(), teamDto.getStats());

        // update scores, to be added to DTO

    }

    public static void updatePlay(Play play, PlayDto playDto, Set<StatPlayer> set){
        StatPlayer minorPlayer = null;

        // seems to work babe
        if(play instanceof Assist assist && playDto instanceof AssistDto assistDto){
            minorPlayer = PlayUtils.findMinorStatPlayer(set,assist);
            PlayUtils.updateAssist(assist, assistDto, minorPlayer);
        }
        else if(play instanceof Block block && playDto instanceof BlockDto blockDto){
            minorPlayer = PlayUtils.findMinorStatPlayer(set, block);
            PlayUtils.updateBlock(block, blockDto, minorPlayer);
        }
        else if(play instanceof Foul foul && playDto instanceof FoulDto foulDto){
            minorPlayer = PlayUtils.findMinorStatPlayer(set, foul);
            PlayUtils.updateFoul(foul, foulDto, minorPlayer);
        }
        else if(play instanceof Rebound rebound && playDto instanceof ReboundDto reboundDto){
            PlayUtils.updateRebound(rebound, reboundDto);
        }
        else if(play instanceof ShotPlay shotPlay && playDto instanceof ShotPlayDto shotPlayDto){
            PlayUtils.updateShotPlay(shotPlay, shotPlayDto);
        }
        else if(play instanceof Steal steal && playDto instanceof StealDto stealDto){
            minorPlayer = PlayUtils.findMinorStatPlayer(set, steal);
            PlayUtils.updateSteal(steal, stealDto, minorPlayer);
        }
        else if(play instanceof Turnover turnover && playDto instanceof TurnoverDto turnoverDto){
            minorPlayer = PlayUtils.findMinorStatPlayer(set, turnover);
            PlayUtils.updateTurnover(turnover, turnoverDto, minorPlayer);
        }
        else if(play instanceof Violation violation && playDto instanceof ViolationDto violationDto){
            PlayUtils.updateViolation(violation, violationDto);
        }
        else{
            throw new RuntimeException("Play not recognized as playDto");
        }
    }

    public static void updateStatPlayer(StatPlayer statPlayer, GameToHandleDto.PlayerDto playerDto, Set<StatPlayer> statPlayerSet){
        statPlayer.setOnCourt(playerDto.getOnCourt());
        statPlayer.setShirtNumber(playerDto.getShirtNumber());
        statPlayer.setStartingFive(playerDto.getStartingFive());
        statPlayer.setPositionOnCourt(playerDto.getPositionOnCourt());
        updateStatLine(statPlayer.getStatLine(), playerDto.getStats());

        for (var play : statPlayer.getPlays()) {
            Optional<PlayDto> matchingPlayDto = PlayUtils.findMatchingPlayDto(play, playerDto.getPlays());
            matchingPlayDto.ifPresent(playDto -> updatePlay(play, playDto, statPlayerSet));
        }

        updateStatLine(statPlayer.getStatLine(), playerDto.getStats());
    }

    public static void updateStatLine(StatLine statLine, StatLineDto statLineDto){
        statLine.setTimeOnCourtInMs(statLineDto.getTimeOnCourtInMs());
        statLine.setTwoAttempted(statLineDto.getTwoAttempted());
        statLine.setTwoMade(statLineDto.getTwoMade());
        statLine.setThreeAttempted(statLineDto.getThreeAttempted());
        statLine.setThreeMade(statLineDto.getThreeMade());
        statLine.setFreeThrowAttempted(statLineDto.getFreeThrowAttempted());
        statLine.setFreeThrowMade(statLineDto.getFreeThrowMade());
        statLine.setTotalPoints(statLineDto.getTotalPoints());
        statLine.setOffRebounds(statLineDto.getOffRebounds());
        statLine.setDefRebounds(statLineDto.getDefRebounds());
        statLine.setAssists(statLineDto.getAssists());
        statLine.setFouls(statLineDto.getFouls());
        statLine.setForcedFouls(statLineDto.getForcedFouls());
        statLine.setTurnovers(statLineDto.getTurnovers());
        statLine.setSteals(statLineDto.getSteals());
        statLine.setBlocks(statLineDto.getBlocks());
        statLine.setBlocksReceived(statLineDto.getBlocksReceived());
        statLine.setEvaluation(statLineDto.getEvaluation());
        statLine.setPlusMinus(statLineDto.getPlusMinus());
        statLine.setPossessions(statLineDto.getPossessions());
    }

    public static void convertPlaysToPlayDto(List<PlayDto> plays) {
        for (int i = 0; i < plays.size(); i++) {
            PlayDto playDto = plays.get(i);
            PlayDto convertedDto = null;
            try {
                switch (playDto.getPlayType()) {
                    case "ASSIST" -> convertedDto = JsonUtils.fromJson(JsonUtils.toJson(playDto), AssistDto.class);
                    case "BLOCK" -> convertedDto = JsonUtils.fromJson(JsonUtils.toJson(playDto), BlockDto.class);
                    case "FOUL" -> convertedDto = JsonUtils.fromJson(JsonUtils.toJson(playDto), FoulDto.class);
                    case "REBOUND" -> convertedDto = JsonUtils.fromJson(JsonUtils.toJson(playDto), ReboundDto.class);
                    case "SHOTPLAY" -> convertedDto = JsonUtils.fromJson(JsonUtils.toJson(playDto), ShotPlayDto.class);
                    case "STEAL" -> convertedDto = JsonUtils.fromJson(JsonUtils.toJson(playDto), StealDto.class);
                    case "TURNOVER" -> convertedDto = JsonUtils.fromJson(JsonUtils.toJson(playDto), TurnoverDto.class);
                    case "VIOLATION" -> convertedDto = JsonUtils.fromJson(JsonUtils.toJson(playDto), ViolationDto.class);
                    default -> throw new RuntimeException("Play not recognized as any DTO class");
                }
            } catch (IOException e) {
                throw new RuntimeException("Error converting playDto to specific type", e);
            }
            plays.set(i, convertedDto);
        }
    }

}

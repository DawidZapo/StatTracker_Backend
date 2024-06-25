package com.stat_tracker.utils;

import com.stat_tracker.dto.game.GameCreatedDto;
import com.stat_tracker.dto.game.GameToHandleDto;
import com.stat_tracker.dto.game.GameWithStatTeamsDto;
import com.stat_tracker.dto.game.GameWithTeamNamesDto;
import com.stat_tracker.dto.plays.*;
import com.stat_tracker.entity.game.Game;
import com.stat_tracker.entity.player.Player;
import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.entity.plays.*;
import com.stat_tracker.entity.plays.abstract_play.Play;
import com.stat_tracker.entity.score.Score;
import com.stat_tracker.entity.team.StatTeam;
import com.stat_tracker.entity.team.Team;

import java.time.LocalDateTime;
import java.util.List;
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

        Long gameDurationInMs = (long) gameCreatedDto.getQuarterLengthMin() * 4 * 60 * 1000;
        game.setTimeRemainingMs(gameDurationInMs);

        StatTeam homeStatTeam = StatTeamAndPlayerUtils.createStatTeam(home,game,true, gameCreatedDto, homePlayers);
        StatTeam awayStatTeam = StatTeamAndPlayerUtils.createStatTeam(away,game, false, gameCreatedDto, awayPlayers);

        game.setHome(homeStatTeam);
        game.setAway(awayStatTeam);


        return game;
    }

    public static GameToHandleDto createGameToHandle(Game game){
        GameToHandleDto gameToHandleDto = new GameToHandleDto();
        gameToHandleDto.setId(game.getId());
        gameToHandleDto.setDateTime(game.getLocalDateTime());
        gameToHandleDto.setSeason(game.getSeason());
        gameToHandleDto.setQuarterLengthMin(game.getQuarterLengthMin());
        gameToHandleDto.setTimeRemainingMs(game.getTimeRemainingMs());

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
        player.setStats(StatsUtils.createStatLineDto(statPlayer.getStatLine()));
        player.setPlays(createPlaysDto(statPlayer.getPlays()));

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
        return plays.stream().map(GameUtils::createPlayDto).collect(Collectors.toList());
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
            default -> throw new RuntimeException("Play not found as an instance of some class");
        };
    }

}

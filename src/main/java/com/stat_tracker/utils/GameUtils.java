package com.stat_tracker.utils;

import com.stat_tracker.dto.game.GameCreatedDto;
import com.stat_tracker.dto.game.GameWithStatTeamsDto;
import com.stat_tracker.dto.game.GameWithTeamNamesDto;
import com.stat_tracker.entity.game.Game;
import com.stat_tracker.entity.player.Player;
import com.stat_tracker.entity.team.StatTeam;
import com.stat_tracker.entity.team.Team;

import java.time.LocalDateTime;
import java.util.List;

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
}

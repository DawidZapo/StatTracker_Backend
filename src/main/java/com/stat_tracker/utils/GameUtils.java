package com.stat_tracker.utils;

import com.stat_tracker.dto.game.GameWithStatTeamsDto;
import com.stat_tracker.dto.game.GameWithTeamNamesDto;
import com.stat_tracker.entity.game.Game;

import java.time.LocalDateTime;

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
}

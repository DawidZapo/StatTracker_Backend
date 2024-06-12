package com.stat_tracker.utils;

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
}

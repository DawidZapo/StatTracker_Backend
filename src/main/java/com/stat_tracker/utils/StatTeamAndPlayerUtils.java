package com.stat_tracker.utils;

import com.stat_tracker.dto.stat_player.StatPlayerDto;
import com.stat_tracker.dto.stat_team.StatTeamDto;
import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.entity.score.Score;
import com.stat_tracker.entity.team.StatTeam;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StatTeamAndPlayerUtils {
    public static StatTeamDto createStatTeamDto(StatTeam statTeam){
        StatTeamDto statTeamDto = new StatTeamDto();
        statTeamDto.setId(statTeam.getTeam().getId());
        statTeamDto.setName(statTeam.getTeam().getName());
        statTeamDto.setAddress(statTeam.getTeam().getAddress());
        statTeamDto.setLocation(statTeam.getTeam().getLocation());
        statTeamDto.setArena(statTeam.getTeam().getArena());

        List<Integer> scores = statTeam.getScores().stream()
                .sorted(Comparator.comparingInt(Score::getPart))
                .map(Score::getWorth)
                .collect(Collectors.toList());

        statTeamDto.setScores(scores);


        statTeamDto.setStatLine(StatsUtils.createStatLineDto(statTeam.getStatLine()));

        List<StatPlayerDto> players = statTeam.getStatPlayers().stream().map(StatTeamAndPlayerUtils::createStatPlayerDto).toList();
        statTeamDto.setPlayers(players);

        return statTeamDto;
    }

    public static StatPlayerDto createStatPlayerDto(StatPlayer player){
        StatPlayerDto statPlayerDto = new StatPlayerDto();
        statPlayerDto.setId(player.getPlayer().getId());
        statPlayerDto.setFirstName(player.getPlayer().getFirstName());
        statPlayerDto.setLastName(player.getPlayer().getLastName());
        statPlayerDto.setShirtNumber(player.getShirtNumber());
        statPlayerDto.setStartingFive(player.getStartingFive());
        statPlayerDto.setStatLine(StatsUtils.createStatLineDto(player.getStatLine()));

        return statPlayerDto;
    }
}

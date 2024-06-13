package com.stat_tracker.utils;

import com.stat_tracker.dto.stat_team.StatTeamDto;
import com.stat_tracker.entity.score.Score;
import com.stat_tracker.entity.team.StatTeam;

import java.util.List;

public class StatTeamAndPlayerUtils {
    public static StatTeamDto createStatTeamDto(StatTeam statTeam){
        StatTeamDto statTeamDto = new StatTeamDto();
        statTeamDto.setId(statTeam.getTeam().getId());
        statTeamDto.setName(statTeam.getTeam().getName());
        statTeamDto.setAddress(statTeam.getTeam().getAddress());
        statTeamDto.setLocation(statTeam.getTeam().getLocation());
        statTeamDto.setArena(statTeam.getTeam().getArena());

        List<Integer> scores = statTeam.getScores().stream().map(Score::getWorth).toList();
        statTeamDto.setScores(scores);

        // set StatLineDto
        // set List<StatPlayerDto>

        return statTeamDto;
    }
}
